package com.ridwan1214.cc4

class Controller(private val callBack: CallBack) {
    fun logicGame(pilihanPlayer: String?, pilihanCom: String?) {

        //player 1 win
        if (((pilihanPlayer == "batu") && (pilihanCom == "gunting")) ||
            ((pilihanPlayer == "kertas") && (pilihanCom == "batu")) ||
            ((pilihanPlayer == "gunting") && (pilihanCom == "kertas"))
        ) callBack.check("1")

        //computer win
        else if (pilihanCom == "batu" && (pilihanPlayer == "gunting") ||
            ((pilihanCom == "kertas") && (pilihanPlayer == "batu")) ||
            ((pilihanCom == "gunting") && (pilihanPlayer == "kertas"))
        ) callBack.check("2")

        //draw
        else
            callBack.check("3")
    }
}