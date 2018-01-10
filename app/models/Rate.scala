package models

case class Rate(sellCoin: Coin, // Amount is assumed to be one unit
                buyCoin: Coin,
                amount: Double) {

}
