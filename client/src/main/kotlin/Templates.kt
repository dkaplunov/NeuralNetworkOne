import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.get
import org.w3c.dom.parsing.DOMParser
import kotlin.browser.document

object Templates {
    fun applayTemplates (rootElement:Element?) {
        val nl = rootElement!!.getElementsByTagName("template")
        for (i in 0..nl.length-1) {
            val elm =nl.get(i)
            val name = elm!!.attributes.get("name")!!.value
            XHttp.get(name).then (fun (res:String) {
                val obj = DOMParser().parseFromString(res, "text/html").documentElement  //.rootElement //!!.firstChild as Node
                applayTemplates(obj as Element)
                elm.replaceWith(obj)
            })

        }

    }
}