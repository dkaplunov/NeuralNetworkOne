import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Promise

class XHttp {
    val xhttp :dynamic = XMLHttpRequest()

    fun get (url:String):XHttp {
        xhttp.open("GET", url, true)
        xhttp.setRequestHeader("Content-type", "text/html;charset=utf-8");
        xhttp.send();
        return this

    }

    fun post (url:String, data:dynamic):XHttp {
        xhttp.open("POST", url, true)
        xhttp.setRequestHeader("Content-type", "application/json;charset=utf-8");
        xhttp.send(JSON.stringify(data));

        return this
    }

    fun then (param:(data:String)->Unit):XHttp {xhttp.onloadend = fun () {param(xhttp.responseText)}; return this}
    fun catch (param:(data:String)->Unit):XHttp {xhttp.onError = fun () {param(xhttp.responseText)}; return this}
}

