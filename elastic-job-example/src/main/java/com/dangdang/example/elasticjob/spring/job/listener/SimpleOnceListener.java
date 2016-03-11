/*
 * *
 *  * Copyright 1999-2015 dangdang.com.
 *  * <p>
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  * 
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  * 
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  * </p>
 *  
 *
 */

package com.dangdang.example.elasticjob.spring.job.listener;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.api.listener.AbstractDistributeOnceElasticJobListener;

import javax.annotation.Resource;

public class SimpleOnceListener extends AbstractDistributeOnceElasticJobListener {

    @Resource
    private FooService fooService;
    
    private final long startedTimeoutMillseconds;
    
    private final long completedTimeoutMillseconds;
    
    public SimpleOnceListener(final long startedTimeoutMillseconds, final long completedTimeoutMillseconds) {
        super(startedTimeoutMillseconds, completedTimeoutMillseconds);
        this.startedTimeoutMillseconds = startedTimeoutMillseconds;
        this.completedTimeoutMillseconds = completedTimeoutMillseconds;
    }
    
    @Override
    public void doBeforeJobExecutedAtLastStarted(final JobExecutionMultipleShardingContext shardingContext) {
        System.out.println("doBeforeJobExecutedAtLastStarted:" + fooService.foo());
    }
    
    @Override
    public void doAfterJobExecutedAtLastCompleted(final JobExecutionMultipleShardingContext shardingContext) {
        System.out.println("doAfterJobExecutedAtLastCompleted:" + startedTimeoutMillseconds + "," + completedTimeoutMillseconds);
    }
}