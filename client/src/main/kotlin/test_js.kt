import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {

    window.onload = {
        Templates.loadTemplates(document.documentElement)

        println(Templates.applyTemplate(Templates.TestData("1", 1)))
    }
}

