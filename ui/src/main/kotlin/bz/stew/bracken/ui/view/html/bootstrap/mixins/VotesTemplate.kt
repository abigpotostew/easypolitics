package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stew.bracken.ui.view.html.bootstrap.Bill
import kotlinx.html.HtmlBodyTag
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.tr

/**
 * Created by stew on 7/4/17.
 */
class VotesTemplate(private val bill: Bill):SubTemplate {
   override fun renderIn(root: HtmlBodyTag) {
      val numRows = 10
      val numColumns = 10
      root.table (Classes.voteTable){
         for (row in 0..numRows) {
            tr {
               for (col in 0..numColumns) {
                  td(Classes.voteCell) {
                     val n = row*numColumns + col
                     if((n+row)%2==0){
                        ac(Classes.voteDem)
                     }else{
                        ac(Classes.voteRep)
                     }
                     +"$n"
                  }
               }
            }
         }
      }
   }
}