package web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Controller
class TestController {

        val counter:Long = 1

        @GetMapping("/")
        fun getIndex() = "index.html"

        @GetMapping("/template")
        fun getTemplate(@RequestParam(value = "name", defaultValue = "templates/templ1.html") name: String) = name
}

@RestController
class HelloController {

        val counter:Long = 1

        @GetMapping("/greeting")
        fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) = Greeting(counter, "Hello, $name")



}



data class Greeting(val id: Long, val content: String)