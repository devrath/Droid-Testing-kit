package com.istudio.core.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Rates (
  @SerialName("AED" ) var AED : Double? = null,
  @SerialName("AFN" ) var AFN : Double? = null,
  @SerialName("ALL" ) var ALL : Double? = null,
  @SerialName("AMD" ) var AMD : Double? = null,
  @SerialName("ANG" ) var ANG : Double? = null,
  @SerialName("AOA" ) var AOA : Double? = null,
  @SerialName("ARS" ) var ARS : Double? = null,
  @SerialName("AUD" ) var AUD : Double? = null,
  @SerialName("AWG" ) var AWG : Double? = null,
  @SerialName("AZN" ) var AZN : Double? = null,
  @SerialName("BAM" ) var BAM : Double? = null,
  @SerialName("BBD" ) var BBD : Int?    = null,
  @SerialName("BDT" ) var BDT : Double? = null,
  @SerialName("BGN" ) var BGN : Double? = null,
  @SerialName("BHD" ) var BHD : Double? = null,
  @SerialName("BIF" ) var BIF : Int?    = null,
  @SerialName("BMD" ) var BMD : Int?    = null,
  @SerialName("BND" ) var BND : Double? = null,
  @SerialName("BOB" ) var BOB : Double? = null,
  @SerialName("BRL" ) var BRL : Double? = null,
  @SerialName("BSD" ) var BSD : Int?    = null,
  @SerialName("BTC" ) var BTC : Double? = null,
  @SerialName("BTN" ) var BTN : Double? = null,
  @SerialName("BWP" ) var BWP : Double? = null,
  @SerialName("BYN" ) var BYN : Double? = null,
  @SerialName("BZD" ) var BZD : Double? = null,
  @SerialName("CAD" ) var CAD : Double? = null,
  @SerialName("CDF" ) var CDF : Double? = null,
  @SerialName("CHF" ) var CHF : Double? = null,
  @SerialName("CLF" ) var CLF : Double? = null,
  @SerialName("CLP" ) var CLP : Double? = null,
  @SerialName("CNH" ) var CNH : Double? = null,
  @SerialName("CNY" ) var CNY : Double? = null,
  @SerialName("COP" ) var COP : Double? = null,
  @SerialName("CRC" ) var CRC : Double? = null,
  @SerialName("CUC" ) var CUC : Int?    = null,
  @SerialName("CUP" ) var CUP : Double? = null,
  @SerialName("CVE" ) var CVE : Double? = null,
  @SerialName("CZK" ) var CZK : Double? = null,
  @SerialName("DJF" ) var DJF : Double? = null,
  @SerialName("DKK" ) var DKK : Double? = null,
  @SerialName("DOP" ) var DOP : Double? = null,
  @SerialName("DZD" ) var DZD : Double? = null,
  @SerialName("EGP" ) var EGP : Double? = null,
  @SerialName("ERN" ) var ERN : Int?    = null,
  @SerialName("ETB" ) var ETB : Double? = null,
  @SerialName("EUR" ) var EUR : Double? = null,
  @SerialName("FJD" ) var FJD : Double? = null,
  @SerialName("FKP" ) var FKP : Double? = null,
  @SerialName("GBP" ) var GBP : Double? = null,
  @SerialName("GEL" ) var GEL : Double? = null,
  @SerialName("GGP" ) var GGP : Double? = null,
  @SerialName("GHS" ) var GHS : Double? = null,
  @SerialName("GIP" ) var GIP : Double? = null,
  @SerialName("GMD" ) var GMD : Double? = null,
  @SerialName("GNF" ) var GNF : Int?    = null,
  @SerialName("GTQ" ) var GTQ : Double? = null,
  @SerialName("GYD" ) var GYD : Double? = null,
  @SerialName("HKD" ) var HKD : Double? = null,
  @SerialName("HNL" ) var HNL : Double? = null,
  @SerialName("HRK" ) var HRK : Double? = null,
  @SerialName("HTG" ) var HTG : Double? = null,
  @SerialName("HUF" ) var HUF : Double? = null,
  @SerialName("IDR" ) var IDR : Double? = null,
  @SerialName("ILS" ) var ILS : Double? = null,
  @SerialName("IMP" ) var IMP : Double? = null,
  @SerialName("INR" ) var INR : Double? = null,
  @SerialName("IQD" ) var IQD : Double? = null,
  @SerialName("IRR" ) var IRR : Double? = null,
  @SerialName("ISK" ) var ISK : Double? = null,
  @SerialName("JEP" ) var JEP : Double? = null,
  @SerialName("JMD" ) var JMD : Double? = null,
  @SerialName("JOD" ) var JOD : Double? = null,
  @SerialName("JPY" ) var JPY : Double? = null,
  @SerialName("KES" ) var KES : Double? = null,
  @SerialName("KGS" ) var KGS : Double? = null,
  @SerialName("KHR" ) var KHR : Int?    = null,
  @SerialName("KMF" ) var KMF : Double? = null,
  @SerialName("KPW" ) var KPW : Int?    = null,
  @SerialName("KRW" ) var KRW : Double? = null,
  @SerialName("KWD" ) var KWD : Double? = null,
  @SerialName("KYD" ) var KYD : Double? = null,
  @SerialName("KZT" ) var KZT : Double? = null,
  @SerialName("LAK" ) var LAK : Double? = null,
  @SerialName("LBP" ) var LBP : Double? = null,
  @SerialName("LKR" ) var LKR : Double? = null,
  @SerialName("LRD" ) var LRD : Double? = null,
  @SerialName("LSL" ) var LSL : Double? = null,
  @SerialName("LYD" ) var LYD : Double? = null,
  @SerialName("MAD" ) var MAD : Double? = null,
  @SerialName("MDL" ) var MDL : Double? = null,
  @SerialName("MGA" ) var MGA : Double? = null,
  @SerialName("MKD" ) var MKD : Double? = null,
  @SerialName("MMK" ) var MMK : Double? = null,
  @SerialName("MNT" ) var MNT : Int?    = null,
  @SerialName("MOP" ) var MOP : Double? = null,
  @SerialName("MRU" ) var MRU : Double? = null,
  @SerialName("MUR" ) var MUR : Double? = null,
  @SerialName("MVR" ) var MVR : Double? = null,
  @SerialName("MWK" ) var MWK : Double? = null,
  @SerialName("MXN" ) var MXN : Double? = null,
  @SerialName("MYR" ) var MYR : Double? = null,
  @SerialName("MZN" ) var MZN : Double? = null,
  @SerialName("NAD" ) var NAD : Double? = null,
  @SerialName("NGN" ) var NGN : Int?    = null,
  @SerialName("NIO" ) var NIO : Double? = null,
  @SerialName("NOK" ) var NOK : Double? = null,
  @SerialName("NPR" ) var NPR : Double? = null,
  @SerialName("NZD" ) var NZD : Double? = null,
  @SerialName("OMR" ) var OMR : Double? = null,
  @SerialName("PAB" ) var PAB : Int?    = null,
  @SerialName("PEN" ) var PEN : Double? = null,
  @SerialName("PGK" ) var PGK : Double? = null,
  @SerialName("PHP" ) var PHP : Double? = null,
  @SerialName("PKR" ) var PKR : Double? = null,
  @SerialName("PLN" ) var PLN : Double? = null,
  @SerialName("PYG" ) var PYG : Double? = null,
  @SerialName("QAR" ) var QAR : Double? = null,
  @SerialName("RON" ) var RON : Double? = null,
  @SerialName("RSD" ) var RSD : Double? = null,
  @SerialName("RUB" ) var RUB : Double? = null,
  @SerialName("RWF" ) var RWF : Double? = null,
  @SerialName("SAR" ) var SAR : Double? = null,
  @SerialName("SBD" ) var SBD : Double? = null,
  @SerialName("SCR" ) var SCR : Double? = null,
  @SerialName("SDG" ) var SDG : Int?    = null,
  @SerialName("SEK" ) var SEK : Double? = null,
  @SerialName("SGD" ) var SGD : Double? = null,
  @SerialName("SHP" ) var SHP : Double? = null,
  @SerialName("SLL" ) var SLL : Double? = null,
  @SerialName("SOS" ) var SOS : Double? = null,
  @SerialName("SRD" ) var SRD : Double? = null,
  @SerialName("SSP" ) var SSP : Double? = null,
  @SerialName("STD" ) var STD : Double? = null,
  @SerialName("STN" ) var STN : Double? = null,
  @SerialName("SVC" ) var SVC : Double? = null,
  @SerialName("SYP" ) var SYP : Double? = null,
  @SerialName("SZL" ) var SZL : Double? = null,
  @SerialName("THB" ) var THB : Double? = null,
  @SerialName("TJS" ) var TJS : Double? = null,
  @SerialName("TMT" ) var TMT : Double? = null,
  @SerialName("TND" ) var TND : Double? = null,
  @SerialName("TOP" ) var TOP : Double? = null,
  @SerialName("TRY" ) var TRY : Double? = null,
  @SerialName("TTD" ) var TTD : Double? = null,
  @SerialName("TWD" ) var TWD : Double? = null,
  @SerialName("TZS" ) var TZS : Int?    = null,
  @SerialName("UAH" ) var UAH : Double? = null,
  @SerialName("UGX" ) var UGX : Double? = null,
  @SerialName("USD" ) var USD : Int?    = null,
  @SerialName("UYU" ) var UYU : Double? = null,
  @SerialName("UZS" ) var UZS : Double? = null,
  @SerialName("VES" ) var VES : Double? = null,
  @SerialName("VND" ) var VND : Double? = null,
  @SerialName("VUV" ) var VUV : Double? = null,
  @SerialName("WST" ) var WST : Double? = null,
  @SerialName("XAF" ) var XAF : Double? = null,
  @SerialName("XAG" ) var XAG : Double? = null,
  @SerialName("XAU" ) var XAU : Double? = null,
  @SerialName("XCD" ) var XCD : Double? = null,
  @SerialName("XDR" ) var XDR : Double? = null,
  @SerialName("XOF" ) var XOF : Double? = null,
  @SerialName("XPD" ) var XPD : Double? = null,
  @SerialName("XPF" ) var XPF : Double? = null,
  @SerialName("XPT" ) var XPT : Double? = null,
  @SerialName("YER" ) var YER : Double? = null,
  @SerialName("ZAR" ) var ZAR : Double? = null,
  @SerialName("ZMW" ) var ZMW : Double? = null,
  @SerialName("ZWL" ) var ZWL : Int?    = null
)