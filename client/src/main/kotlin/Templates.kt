import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.get
import org.w3c.dom.parsing.DOMParser

object Templates {
    fun loadTemplates (rootElement:Element?) {
        val nl = rootElement!!.getElementsByTagName("template")
        for (i in 0..nl.length-1) {
            val elm =nl.get(i)
            val name = elm!!.attributes.get("name")!!.value
            XHttp().get(name).then (fun (res:String) {
                val obj = DOMParser().parseFromString(res, "text/html").activeElement!!.firstChild as Node
                loadTemplates(obj as Element)
                elm.replaceWith(obj)
            })

        }
    }

    //TODO Потом как нибудь...
    fun applyTemplate (obj:dynamic) {
        return obj["prop1"]
    }

}