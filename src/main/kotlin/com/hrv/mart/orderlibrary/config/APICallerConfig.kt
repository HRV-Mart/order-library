package com.hrv.mart.orderlibrary.config

import com.hrv.mart.apicall.APICaller
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class APICallerConfig
{
    @Bean
    fun getAPICall() =
        APICaller(
            webClientBuilder = WebClient.builder()
        )
}