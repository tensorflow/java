/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.keras.utils;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Display a progess bar either to the Console or System.out.
 * 
 * @author jbclarke
 */
public class ProgressBar {
    private final static double MICRO_SECONDS = 1E-3;
    private final static long MILLI_SECOND = 1l;
    private final static long SECOND = 1000L;
    private final static long MINUTE = 60L * SECOND;
    private final static long HOUR = 60L * MINUTE;
    
    private Integer target;
    private int width;
    private boolean verbose;
    private long interval;
    private List<String> statefulMetrics;
    private String unit;
    
    private int totalWidth;
    private int seenSoFar;
    private final Map<String, Number[]> values = new HashMap<>();
    private final List<String> valuesOrder = new ArrayList<>();
    
    private final long start = System.currentTimeMillis();
    private long lastUpdate = 0;
    
    // will be null if console is not present
    private final Console console = System.console();
    
    /**
     * Create a ProgressBar
     */
    public ProgressBar() {
        this(null, 30, true, 50, "step", null );
        
    }
    
    /**
     * Create a ProgressBar
     * 
     * @param target Total number of steps expected, null if unknown.
     */
    public ProgressBar(Integer target) {
        this(target, 30, true, 50, "step", null );
        
    }
    
    /**
     * Create a ProgressBar
     * 
     * @param target Total number of steps expected, null if unknown.
     * @param verbose Verbosity mode
     * @param unit Display name for step counts (usually "step" or "sample").
     * @param statefulMetrics 
     */
    public ProgressBar(Integer target, boolean verbose, String unit, List<String> statefulMetrics) {
        this(target, 30, verbose, 50, unit, statefulMetrics );
        
    }
    
    
    
    /**
     * Create a ProgressBar
     * 
     * @param target Total number of steps expected, null if unknown.
     * @param width Progress bar width on screen.
     * @param verbose Verbosity mode
     * @param interval Minimum visual progress update interval (in milliseconds).
     * @param unit Display name for step counts (usually "step" or "sample").
     * @param statefulMetrics 
     */
    public ProgressBar(Integer target, int width, boolean verbose, long interval, String unit, List<String> statefulMetrics) {
        this.target = target;
        this.width = width;
        this.verbose = verbose;
        this.interval = interval;
        this.unit = unit;
        this.statefulMetrics = statefulMetrics == null ? Collections.EMPTY_LIST : statefulMetrics;
    }
    
    /**
     * Updates the progress bar.
     * 
     * @param current Index of current step.
     * @param values List of Pairs: `(name, value_for_last_step)`. If `name` is in
     * `statefulMetrics`, `value_for_last_step` will be displayed as-is.
     * Else, an average of the metric over time will be displayed.
     */
    public void update(Integer current, List<Entry<String, Number>> values) {
        update(current, values, null);
    }
    
    /**
     * Updates the progress bar.
     * 
     * @param current  Index of current step.
     * @param values  List of Entry: `(name, value_for_last_step)`. If `name` is in
     * `statefulMetrics`, `value_for_last_step` will be displayed as-is.
     * Else, an average of the metric over time will be displayed.
     * @param finalize Whether this is the last update for the progress bar. If
     * `None`, defaults to `current >= self.target`.
     */
    public void update(Integer current, List<Entry<String, Number>> values, Boolean finalize) {
        if(finalize == null) {
            if(this.getTarget() == null) {
                finalize = false;
            }else {
                finalize = current >= this.getTarget();
            }
        }
        values = values == null ? Collections.EMPTY_LIST : values;
        
        values.forEach(e -> {
            if(!this.valuesOrder.contains(e.getKey())) {
                this.valuesOrder.add(e.getKey());
            }
            if(!this.statefulMetrics.contains(e.getKey())) {
                int valueBase = Math.max(current - this.seenSoFar, 1);
                if(!this.values.containsKey(e.getKey())) {
                    this.values.put(e.getKey(), new Number[] { e.getValue().doubleValue() * valueBase, valueBase});
                }else {
                    Number[] n = this.values.get(e.getKey());
                    n[0] = n[0].doubleValue() + e.getValue().doubleValue() * valueBase;
                    n[1] = n[1].doubleValue() + valueBase;
                }
            }else {
                this.values.put(e.getKey(),new Number[] { e.getValue().doubleValue(), 1});
            }
            
        });
        
        this.seenSoFar = current;
        long now = System.currentTimeMillis();
        
        StringBuilder info = new StringBuilder(String.format(" - %.0fs", (double)(now - this.start)/(double)SECOND));
        
        if(this.verbose) {
            if(now - this.lastUpdate < this.interval && !finalize) {
                return;
            }
        }
        int prevTotalWidth = this.totalWidth;
        if (this.console != null) {
            for(int i = 0; i < prevTotalWidth; i++) {
                this.console.printf("\b");
            }
            this.console.printf("\r");
        } else {
            System.out.println();
        }
        StringBuilder bar = new StringBuilder();
        if(this.getTarget() != null) {
            int numDigits = (int)Math.log10(this.getTarget()) + 1;
            bar.append(String.format("%" + numDigits + "d/%d [", current, this.getTarget()));
            double prog = (double)current / this.getTarget();
            int progWidth = (int)(this.width * prog);
            if(progWidth >= 0) {
                for(int i = 0; i < progWidth-1; i++) 
                    bar.append('=');
                if(current < this.getTarget())
                    bar.append('>');
                else
                    bar.append('=');
                for(int i = 0; i < this.width - progWidth; i++)
                    bar.append('.');
                bar.append(']');
            }
        }else {
            bar.append(String.format("%7d/Unknown", current));
        }
        this.totalWidth = bar.length();
        if(this.console != null) {
            this.console.printf(bar.toString());
        }else {
            System.out.print(bar.toString());
        }
        // in millis
        double timePerUnit = current != null ? (double)(now - this.start) / current: 0.0;
        if(this.getTarget() == null || finalize) {
            if(timePerUnit >= SECOND || timePerUnit == 0.0) { // seconds
                info.append(String.format(" %.0fs/%s", timePerUnit/SECOND, this.unit));
            }else if(timePerUnit >= 1) { // milliseconds
                info.append(String.format(" %.0fms/%s", timePerUnit, this.unit));
            }else { // microseconds
                info.append(String.format(" %.0fus/%s", timePerUnit*SECOND, this.unit));
            }
        }else {
           double eta = timePerUnit * (this.getTarget() - current); 
           String etaFormat;
           if(eta >= HOUR) { // greater than an hour
               etaFormat = String.format("%d:%02d:%02d",  // hh:mm:ss
                       (int) (eta / HOUR), 
                       (int) (eta / HOUR /  MINUTE), 
                       (int) (eta % MINUTE)/SECOND);
           }else if(eta >= MINUTE) { 
               etaFormat = String.format("%d:%02d",  // mm:ss
                       (int) (eta / MINUTE), 
                       (int) (eta % MINUTE)/SECOND);
           }else {
               etaFormat = String.format("%ds", (int)(eta/SECOND));
           }
           info.append(" - ETA: ").append(etaFormat);
        }
           
        this.valuesOrder.forEach(key -> {
            info.append(String.format(" - %s:", key));
            Number[] val = this.values.get(key);
            double avg = val[0].doubleValue() / Math.max(1.0, val[1].doubleValue());
            if(Math.abs(avg) > 1e-3)
                 info.append(String.format(" %.4f", avg));
             else
                info.append(String.format(" %.4e", avg));
        });
        this.totalWidth += info.length();
        if(prevTotalWidth > this.totalWidth) 
            for(int i = 0; i < prevTotalWidth - this.totalWidth; i++)
                info.append(' ');
        if(finalize) {
            info.append('\n');
        }

        if(this.console != null) {
           console.printf(info.toString());
           console.flush();
       }else {
           System.out.print(info.toString());
           System.out.flush();
       }
        this.lastUpdate = now;
    }
    
    /**
     * updates the progress bar by one unit
     */
    public void increment() {
        add(1);
    }
    
    /**
     * updates the progress bar by one unit
     * 
     * @param values List of Entry: `(name, value_for_last_step)`. If `name` is in
     * `statefulMetrics`, `value_for_last_step` will be displayed as-is.
     * Else, an average of the metric over time will be displayed.
     */
    public void increment(List<Entry<String, Number>> values) {
        add(1, values);
    }
    
    /**
     * update the progress bar 
     * 
     * @param n the number of units to add to the current number
     */
    public void add(int n) {
        add(n, null);
    }
    
    /**
     * update the progress bar 
     * 
     * @param n the number of units to add to the current number
     * @param values List of Entry: `(name, value_for_last_step)`. If `name` is in
     * `statefulMetrics`, `value_for_last_step` will be displayed as-is.
     * Else, an average of the metric over time will be displayed.
     */
    public void add(int n, List<Entry<String, Number>> values) {
        this.update(this.seenSoFar + n, values);
    }

    /**
     * @return the target
     */
    public Integer getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(Integer target) {
        this.target = target;
    }
}

