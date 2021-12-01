import ch.qos.logback.classic.Level
import ch.qos.logback.classic.encoder.PatternLayoutEncoder

import java.nio.charset.Charset

def appenderList = ["CONSOLE"]
def appName = System.getenv("SERVICE_NAME") ?: "example"
def appHost = System.getenv("HOST")
def profile = System.getenv("SPRING_PROFILES_ACTIVE")

def level = System.getenv("LOG_LEVEL")
if (null == level || level == "") {
    level = "INFO"
}

println "=" * 80
println """
    APP NAME          : $appName
    APP HOST          : $appHost
    APP PROFILE       : $profile
    LOGGING LEVEL     : $level
"""
println "=" * 80

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName("UTF-8")
        pattern = "%-4relative %d %-5level [ %t ] %-55logger{13} | %m %n"
    }
}

logger('org.springframework', WARN)
root(Level.valueOf(level), appenderList)