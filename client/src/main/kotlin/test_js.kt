import org.w3c.dom.parsing.DOMParser
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {

    window.onload = {

        XHttp.get("/template").then (fun (res:String) {
            val obj = DOMParser().parseFromString(res, "text/html")
            document.getElementById("TestPlace")!!.append(obj.activeElement!!.firstChild)
        })

    }
}

