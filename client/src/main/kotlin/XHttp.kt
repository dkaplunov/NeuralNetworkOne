import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Promise

object XHttp {
    var xhttp :dynamic= XMLHttpRequest();

/*    fun doRequest (method:String, url:String) = Promise (q:String, p:RuntimeException -> {
        xhttp.open(method, url, true)

    })
*/

   val qq = {method:String, url:String -> {
        xhttp.open(method, url, true)

    }}



}

