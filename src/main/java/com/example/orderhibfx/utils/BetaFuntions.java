package com.example.orderhibfx.utils;

import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Request;


import java.util.ArrayList;
import java.util.Collections;

public class BetaFuntions {


    //por defecto se le pasa ""
    public static ArrayList<Request> getAllRequest(String command){
        var handlerRequest = new RequestDAO();
        return handlerRequest.getAll(command);
    }

    public static void insertRequest(Request request){
        var handlerRequest = new RequestDAO();
        handlerRequest.save(request);
    }

    public static void deleteRequest(Request request){
        var handlerRequest = new RequestDAO();
        handlerRequest.delete(request);
    }

    public static void updateRequest(Request request){
        var handlerRequest = new RequestDAO();
        handlerRequest.update(request);
    }

    public static void checkDeliveredRequest(Request request){
        request.setDelivered((byte) 1);
        updateRequest(request);
    }

    public static ArrayList<Request> allRequestByClient(){
        return getAllRequest(" as rq order by rq.client");
    }

    public static  ArrayList<Request> allRequestByDate(){
        return getAllRequest(" as rq order by rq.date");
    }

    public static ArrayList<Request> allRequestNotDelivered(){
        return getAllRequest(" as rq where rq.delivered = 0");
    }

    public static ArrayList<Request> allRequestLastWeek(){
        return getAllRequest(" as rq where rq.date >= current_date-7");
    }

    public static ArrayList<Request> allRequestLastMonth(){
        return getAllRequest(" as rq where rq.date >= current_date-30");
    }

    public static ArrayList<Request> allRequestLastYear(){
        return getAllRequest(" as rq where rq.date >= current_date-365");
    }





}
