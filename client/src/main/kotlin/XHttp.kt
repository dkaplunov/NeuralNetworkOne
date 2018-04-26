import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Promise

object XHttp {
    val xhttp :dynamic = XMLHttpRequest()

    fun doReq (method:String, url:String):XHttp {
        xhttp.open(method, url, true)
        xhttp.send();
        return this
    }

    fun get (url:String):XHttp = doReq("GET", url)
    fun post (url:String):XHttp = doReq("POST", url)

    fun then (param:(data:String)->Unit):XHttp {xhttp.onloadend = fun () {param(xhttp.responseText)}; return this}
    fun catch (param:(data:String)->Unit):XHttp {xhttp.onError = fun () {param(xhttp.responseText)}; return this}
}

