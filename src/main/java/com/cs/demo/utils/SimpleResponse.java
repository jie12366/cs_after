package com.cs.demo.utils;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/8 8:47
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content){
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}