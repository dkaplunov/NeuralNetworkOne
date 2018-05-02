package web

import data.NnParams
import data.NnValues
import model.NnOperator
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

@Controller
class TestController {

        val counter:Long = 1

        @GetMapping("/")
        fun getIndex() = "index.html"

        @GetMapping("/template")
        fun getTemplate(@RequestParam(value = "name", defaultValue = "templates/templ1.html") name: String) = name
}

@RestController
class CreateNNManipulationController {

        @PostMapping("/createNet")
        fun createNet(@RequestBody initData: NnParams, session:HttpSession):NnValues {
            session.setAttribute("nNentwork", NnOperator (initData))
            return NnValues(10)
        }

    @PostMapping("/executeNet")
    fun executeNet(@RequestBody initData: NnValues, session:HttpSession):NnValues =
        (session.getAttribute("nNentwork") as NnOperator).execute(initData)

}

