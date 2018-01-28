package bz.stewart.bracken.web.view.map

import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.FlowContent
import kotlinx.html.canvas
import kotlinx.html.div
import kotlinx.html.id
import kotlinx.html.img

class MapView() : ViewRender {
    override fun renderIn(parent: FlowContent, context: WebPageContext) {
        parent.div {
//            img {
//                src = "/img/usaHigh.svg"
//            }
            canvas {
                id = "mapCanvas"
                //attributes.put("resize","")
            }
            /* css for the us map
            width: 1223px;
            height: 765;
            transform: translateX(-100px);
             */
        }
    }
}

