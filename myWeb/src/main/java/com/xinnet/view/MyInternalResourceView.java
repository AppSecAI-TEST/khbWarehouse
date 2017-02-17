package com.xinnet.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.InternalResourceView;



/**
 * 轻量级的InternalResourceView
 * 
 * 不支持jsp标签、不支持request、session、application等对象
 */
public class MyInternalResourceView extends InternalResourceView {

    private static final String CONTEXT_PATH = "ctx";

    @Override
    protected void exposeHelpers(HttpServletRequest request) throws Exception {
    	request.setAttribute(CONTEXT_PATH, request.getContextPath());
        super.exposeHelpers(request);
    }
}