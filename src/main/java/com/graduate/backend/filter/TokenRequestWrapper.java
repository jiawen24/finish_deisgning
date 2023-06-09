package com.graduate.backend.filter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

//重写RequestWrapper实现自定义添加header
public class TokenRequestWrapper extends HttpServletRequestWrapper {
        private final Map<String, String> customHeaders;

        public TokenRequestWrapper(HttpServletRequest request){
            super(request);
            this.customHeaders = new HashMap<>();
        }

        public void putHeader(String name, String value){
            this.customHeaders.put(name, value);
        }

        @Override
        public String getHeader(String name) {
            String headerValue = customHeaders.get(name);

            if (headerValue != null){
                return headerValue;
            }
            return ((HttpServletRequest) getRequest()).getHeader(name);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            if (customHeaders.isEmpty()) {
                return super.getHeaderNames();
            }

            Set<String> set = new HashSet<String>(customHeaders.keySet());
            // 添加自定义header
            Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
            while (e.hasMoreElements()) {
                String n = e.nextElement();
                set.add(n);
            }

            return Collections.enumeration(set);
        }
}
