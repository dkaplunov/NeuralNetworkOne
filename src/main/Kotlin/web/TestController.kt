package web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

        val counter:Long = 1

        @GetMapping("/greeting")
        fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
                Greeting(counter, "Hello, $name")

}

data class Greeting(val id: Long, val content: String)