/*******************************************************************************
 * Copyright (c) 2016 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/ 
package application.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codahale.metrics.Counter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

@WebServlet(urlPatterns="/servlet")
public class LibertyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	//declare a metrics registry object to register our metrics
    public static MetricRegistry METRIC_REGISTRY = new MetricRegistry();

    //define 2 metrics
    private Counter counter1;
    private Timer timer1;
    @Override
    public void init() throws ServletException
    {
    	counter1 = METRIC_REGISTRY.counter("counter1");
    	timer1 = METRIC_REGISTRY.timer("timer1");
        System.out.println("Servlet " + this.getServletName() + " has started.");
        final JmxReporter reporter = JmxReporter.forRegistry(METRIC_REGISTRY).build();
        reporter.start();
}
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //calculate request time
        Timer.Context context = timer1.time();
        response.getWriter().println("Welcome to the Metrics Sample app");
        response.getWriter().println("");
        //increment request counter by 1
        counter1.inc();
        context.stop();
        response.getWriter().println(String.format("Request count: %d", counter1.getCount()));
        response.getWriter().println(String.format("Time to process the request: %fms", timer1.getMeanRate()));
    }

    

}
