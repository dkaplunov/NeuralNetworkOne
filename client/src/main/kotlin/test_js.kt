import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.w3c.dom.get
import org.w3c.dom.parsing.DOMParser
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {

    window.onload = {
        Templates.applayTemplates(document.documentElement)
    }
}

