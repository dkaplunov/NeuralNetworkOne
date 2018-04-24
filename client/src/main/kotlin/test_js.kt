import kotlinx.html.dom.append
import kotlinx.html.js.span
import org.w3c.dom.HTMLElement
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.appendText

fun main(args: Array<String>) {

    println("Hello!")

    window.onload = {

        var xhttp :dynamic= XMLHttpRequest();
        xhttp.open("GET", "/greeting", true);
        xhttp.onloadend=fun(){
            println(xhttp.readyState)
            println(xhttp.status)
            println(xhttp.responseText)

            val obj = JSON.parse<Greeting>(xhttp.responseText)

            val elm = document.getElementById("TestPlace") as HTMLElement
            elm.append.span { text("Привет, блин! "+obj.id+" -- "+obj.content) }

//            document.getElementById("TestPlace")!!.appendText("Привет, блин! "+obj.id+" -- "+obj.content)

        }
        xhttp.send();
    }
}

data class Greeting(val id: Long, val content: String)
