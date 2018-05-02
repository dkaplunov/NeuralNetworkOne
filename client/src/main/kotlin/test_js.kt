import data.NnParams
import data.NnValues
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {

    window.onload = {
        Templates.loadTemplates(document.documentElement)

        XHttp().post("/createNet", NnParams(arrayOf(2,4,1),"_", "_", 100000, "_")).then {
            XHttp().post("/executeNet", NnValues(2)).then (fun (res:String) { println(res) })
        }
    }

}



