package com.sevenfive.assignment4.demo;

import com.sevenfive.assignment4.auth.Auth;
import com.sevenfive.assignment4.auth.models.User;
import com.sevenfive.assignment4.ui.RequestUtils;
import spark.ModelAndView;
import spark.Request;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import static spark.Spark.*;

public class AppServer {
    
    public static void main(String[] args) {

        final TemplateEngine engine = new FreeMarkerEngine();
        staticFileLocation("/public");

        //home page request
        get("/",(req,res)->{
                res.header("Cache-Control", "no-cache, no-store");
                Map<String,Object> map = new HashMap<>();
                map.put("uname",Auth.getInstance().getUser(req.session().attribute("id")).getUsername());
                map.put("role",Auth.getInstance().getUser(req.session().attribute("id")).getRole());
                return new ModelAndView(map,"home.ftl");

        },engine);

        get("/id",((request, response) -> {
           String id =  request.cookie("id");
          // System.out.println("Id recieved :"+id);
           return 200;
        }));

        get("/logout",(request, response) -> {
            String token = request.session().attribute("id");
            sessionInvalidationCode(request,token);
            return new ModelAndView(null,"logout.ftl");

        },engine);

        get("/login",((request, response) -> {
            Map<String,String> map = new HashMap<>();
            return new ModelAndView(map,"login.ftl");
        }),engine);

        post("/login",(((request, response) -> {
           //extract the creds
            Map<String,String> keyMap = RequestUtils.valuesFromRequest(request.body());
            boolean verified = Auth.getInstance().verifyCredentials(request.session().attribute("id"),keyMap.getOrDefault("uname",""),keyMap.getOrDefault("psw",""));
            if(verified){
                //take to home page
                //User authenticated
                String token = request.session().attribute("id");
                Auth.getInstance().authenticateUser(token,true); //authenticated the token, annon user verified
                response.redirect("/");
                halt();
            }
                return "Credentials not authenticated, please check them and try again";

        })));


        get("/admin",(request, response) -> {

            String token = request.session().attribute("id");
            User user  = Auth.getInstance().getUser(token);
            Map<String,String> map = new HashMap<>();
            map.put("uname",user.getUsername());
            map.put("role", Auth.Role.ADMIN.toString());
            //Check to see if it's authorized
            if(!Auth.getInstance().isAuthorized(token, Auth.Role.ADMIN))
            {
                return new ModelAndView(map,"notauthorized.ftl");

            }


            return new ModelAndView(map,"content.ftl");
        },engine);


        get("/customer",(request, response) -> {

            String token = request.session().attribute("id");
            User user  = Auth.getInstance().getUser(token);
            Map<String,String> map = new HashMap<>();
            map.put("uname",user.getUsername());
            map.put("role", Auth.Role.CUSTOMER.toString());
            //Check to see if it's authorized
            if(!Auth.getInstance().isAuthorized(token, Auth.Role.CUSTOMER))
            {
                return new ModelAndView(map,"notauthorized.ftl");

            }


            return new ModelAndView(map,"content.ftl");
        },engine);

        get("/vendor",(request, response) -> {

            String token = request.session().attribute("id");
            User user  = Auth.getInstance().getUser(token);
            Map<String,String> map = new HashMap<>();
            map.put("uname",user.getUsername());
            map.put("role", Auth.Role.VENDOR.toString());
            //Check to see if it's authorized
            if(!Auth.getInstance().isAuthorized(token, Auth.Role.VENDOR))
            {
                return new ModelAndView(map,"notauthorized.ftl");

            }


            return new ModelAndView(map,"content.ftl");
        },engine);



        //Ajax session invalidation call
        get("/stinvalidaton",(request, response) -> {
            try{
                String token = request.session().attribute("id");
                sessionInvalidationCode(request,token);
            }catch (Exception ignored){};
            return true;
        });

        //this filter will rum before every request
        before(((request, response) -> {
        String token = request.session().attribute("id");
        if(token==null || token.trim().isEmpty()){
            //first time user, assign them a uuid
            token = UUID.randomUUID().toString();
            Auth.getInstance().logAnnonUser(token);
            request.session().attribute("id",token);
            response.redirect("/login");
            halt();

        }
        else{
            boolean isLoggedIn =  Auth.getInstance().isLoggedIn(token);
            if(!isLoggedIn && !request.uri().equals("/login"))
            {halt(401,"Not Authenticated. Please login and verify yourself first. <a href=\"/login\">Please click here to login</a>");}
        }

        }));

        post("/log",((request, response) -> {

          System.out.println("Log from browser:"+request.body());
          return true;
        }));
    }

    private static void sessionInvalidationCode(Request request,String token){
        Auth.getInstance().invalidateSession(token);
        request.session().attribute("id",null);
        request.session().invalidate(); //invalidating the session
    }

}
