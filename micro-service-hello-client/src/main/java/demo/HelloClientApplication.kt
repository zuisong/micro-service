package demo

import feign.Logger
import org.slf4j.*
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.boot.task.*
import org.springframework.cloud.client.discovery.*
import org.springframework.cloud.openfeign.*
import org.springframework.context.annotation.*
import org.springframework.scheduling.annotation.*
import org.springframework.stereotype.*
import java.util.concurrent.*


fun main(args: Array<String>) {
  runApplication<HelloClientApplication>(*args)
}


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class HelloClientApplication {

  @Bean
  fun feignLoggerLevel(): Logger.Level {
    return Logger.Level.FULL
  }

}


@Configuration
class ScheduledGracefulShutdownConfig {
  //this shouldn't be necessary at all with ' spring.task.scheduling.shutdown.await-termination'. but neither works
  @Bean
  fun taskSchedulerCustomizer(): ThreadPoolTaskSchedulerCustomizer {
    return ThreadPoolTaskSchedulerCustomizer { taskScheduler ->
      taskScheduler.setWaitForTasksToCompleteOnShutdown(true)
      taskScheduler.setAwaitTerminationSeconds(60)
    }
  }
}

@Service
class ScheduledService {
  private val LOGGER = LoggerFactory.getLogger(ScheduledService::class.java)

  @Scheduled(fixedRate = 100000000L)
  @Throws(InterruptedException::class)
  fun scheduled() {
    LOGGER.info("Starting scheduled job...")
    TimeUnit.MINUTES.sleep(5)
    LOGGER.info("Scheduled job finished.")
  }
}
