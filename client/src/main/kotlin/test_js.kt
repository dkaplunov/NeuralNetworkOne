import org.w3c.dom.parsing.DOMParser
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {

    println("Hello!")

    window.onload = {

        var xhttp :dynamic= XMLHttpRequest();
        xhttp.open("GET", "/template", true);
        xhttp.onloadend=fun(){
            println(xhttp.readyState)
            println(xhttp.status)
            println(xhttp.responseText)

//            val obj = JSON.parse<Greeting>(xhttp.responseText)

            val obj = DOMParser().parseFromString(xhttp.responseText, "text/html")

//            val elm = document.getElementById("TestPlace") as HTMLElement
//            elm.append.span { text("Привет, блин! "+obj.id+" -- "+obj.content) }

            document.getElementById("TestPlace")!!.append(obj.activeElement!!.firstChild)

        }
        xhttp.send();
    }
}

data class Greeting(val id: Long, val content: String)
