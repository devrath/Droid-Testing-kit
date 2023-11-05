package com.istudio.mock_factory.generators

import com.google.gson.Gson
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues

object FakeApiData {

    fun getFakeApiCurriencies() : Currencies {
        return Gson().fromJson(curriencyMockData, Currencies::class.java)
    }

    fun getFakeApiCurriencyConversionValues() : CurrencyConversionValues {
        return Gson().fromJson(curriencyConversionMockData, CurrencyConversionValues::class.java)
    }

}

private const val curriencyMockData = "{\n" +
        "  \"AED\": \"United Arab Emirates Dirham\",\n" +
        "  \"AFN\": \"Afghan Afghani\",\n" +
        "  \"ALL\": \"Albanian Lek\",\n" +
        "  \"AMD\": \"Armenian Dram\",\n" +
        "  \"ANG\": \"Netherlands Antillean Guilder\",\n" +
        "  \"AOA\": \"Angolan Kwanza\",\n" +
        "  \"ARS\": \"Argentine Peso\",\n" +
        "  \"AUD\": \"Australian Dollar\",\n" +
        "  \"AWG\": \"Aruban Florin\",\n" +
        "  \"AZN\": \"Azerbaijani Manat\",\n" +
        "  \"BAM\": \"Bosnia-Herzegovina Convertible Mark\",\n" +
        "  \"BBD\": \"Barbadian Dollar\",\n" +
        "  \"BDT\": \"Bangladeshi Taka\",\n" +
        "  \"BGN\": \"Bulgarian Lev\",\n" +
        "  \"BHD\": \"Bahraini Dinar\",\n" +
        "  \"BIF\": \"Burundian Franc\",\n" +
        "  \"BMD\": \"Bermudan Dollar\",\n" +
        "  \"BND\": \"Brunei Dollar\",\n" +
        "  \"BOB\": \"Bolivian Boliviano\",\n" +
        "  \"BRL\": \"Brazilian Real\",\n" +
        "  \"BSD\": \"Bahamian Dollar\",\n" +
        "  \"BTC\": \"Bitcoin\",\n" +
        "  \"BTN\": \"Bhutanese Ngultrum\",\n" +
        "  \"BWP\": \"Botswanan Pula\",\n" +
        "  \"BYN\": \"Belarusian Ruble\",\n" +
        "  \"BZD\": \"Belize Dollar\",\n" +
        "  \"CAD\": \"Canadian Dollar\",\n" +
        "  \"CDF\": \"Congolese Franc\",\n" +
        "  \"CHF\": \"Swiss Franc\",\n" +
        "  \"CLF\": \"Chilean Unit of Account (UF)\",\n" +
        "  \"CLP\": \"Chilean Peso\",\n" +
        "  \"CNH\": \"Chinese Yuan (Offshore)\",\n" +
        "  \"CNY\": \"Chinese Yuan\",\n" +
        "  \"COP\": \"Colombian Peso\",\n" +
        "  \"CRC\": \"Costa Rican Colón\",\n" +
        "  \"CUC\": \"Cuban Convertible Peso\",\n" +
        "  \"CUP\": \"Cuban Peso\",\n" +
        "  \"CVE\": \"Cape Verdean Escudo\",\n" +
        "  \"CZK\": \"Czech Republic Koruna\",\n" +
        "  \"DJF\": \"Djiboutian Franc\",\n" +
        "  \"DKK\": \"Danish Krone\",\n" +
        "  \"DOP\": \"Dominican Peso\",\n" +
        "  \"DZD\": \"Algerian Dinar\",\n" +
        "  \"EGP\": \"Egyptian Pound\",\n" +
        "  \"ERN\": \"Eritrean Nakfa\",\n" +
        "  \"ETB\": \"Ethiopian Birr\",\n" +
        "  \"EUR\": \"Euro\",\n" +
        "  \"FJD\": \"Fijian Dollar\",\n" +
        "  \"FKP\": \"Falkland Islands Pound\",\n" +
        "  \"GBP\": \"British Pound Sterling\",\n" +
        "  \"GEL\": \"Georgian Lari\",\n" +
        "  \"GGP\": \"Guernsey Pound\",\n" +
        "  \"GHS\": \"Ghanaian Cedi\",\n" +
        "  \"GIP\": \"Gibraltar Pound\",\n" +
        "  \"GMD\": \"Gambian Dalasi\",\n" +
        "  \"GNF\": \"Guinean Franc\",\n" +
        "  \"GTQ\": \"Guatemalan Quetzal\",\n" +
        "  \"GYD\": \"Guyanaese Dollar\",\n" +
        "  \"HKD\": \"Hong Kong Dollar\",\n" +
        "  \"HNL\": \"Honduran Lempira\",\n" +
        "  \"HRK\": \"Croatian Kuna\",\n" +
        "  \"HTG\": \"Haitian Gourde\",\n" +
        "  \"HUF\": \"Hungarian Forint\",\n" +
        "  \"IDR\": \"Indonesian Rupiah\",\n" +
        "  \"ILS\": \"Israeli New Sheqel\",\n" +
        "  \"IMP\": \"Manx pound\",\n" +
        "  \"INR\": \"Indian Rupee\",\n" +
        "  \"IQD\": \"Iraqi Dinar\",\n" +
        "  \"IRR\": \"Iranian Rial\",\n" +
        "  \"ISK\": \"Icelandic Króna\",\n" +
        "  \"JEP\": \"Jersey Pound\",\n" +
        "  \"JMD\": \"Jamaican Dollar\",\n" +
        "  \"JOD\": \"Jordanian Dinar\",\n" +
        "  \"JPY\": \"Japanese Yen\",\n" +
        "  \"KES\": \"Kenyan Shilling\",\n" +
        "  \"KGS\": \"Kyrgystani Som\",\n" +
        "  \"KHR\": \"Cambodian Riel\",\n" +
        "  \"KMF\": \"Comorian Franc\",\n" +
        "  \"KPW\": \"North Korean Won\",\n" +
        "  \"KRW\": \"South Korean Won\",\n" +
        "  \"KWD\": \"Kuwaiti Dinar\",\n" +
        "  \"KYD\": \"Cayman Islands Dollar\",\n" +
        "  \"KZT\": \"Kazakhstani Tenge\",\n" +
        "  \"LAK\": \"Laotian Kip\",\n" +
        "  \"LBP\": \"Lebanese Pound\",\n" +
        "  \"LKR\": \"Sri Lankan Rupee\",\n" +
        "  \"LRD\": \"Liberian Dollar\",\n" +
        "  \"LSL\": \"Lesotho Loti\",\n" +
        "  \"LYD\": \"Libyan Dinar\",\n" +
        "  \"MAD\": \"Moroccan Dirham\",\n" +
        "  \"MDL\": \"Moldovan Leu\",\n" +
        "  \"MGA\": \"Malagasy Ariary\",\n" +
        "  \"MKD\": \"Macedonian Denar\",\n" +
        "  \"MMK\": \"Myanma Kyat\",\n" +
        "  \"MNT\": \"Mongolian Tugrik\",\n" +
        "  \"MOP\": \"Macanese Pataca\",\n" +
        "  \"MRU\": \"Mauritanian Ouguiya\",\n" +
        "  \"MUR\": \"Mauritian Rupee\",\n" +
        "  \"MVR\": \"Maldivian Rufiyaa\",\n" +
        "  \"MWK\": \"Malawian Kwacha\",\n" +
        "  \"MXN\": \"Mexican Peso\",\n" +
        "  \"MYR\": \"Malaysian Ringgit\",\n" +
        "  \"MZN\": \"Mozambican Metical\",\n" +
        "  \"NAD\": \"Namibian Dollar\",\n" +
        "  \"NGN\": \"Nigerian Naira\",\n" +
        "  \"NIO\": \"Nicaraguan Córdoba\",\n" +
        "  \"NOK\": \"Norwegian Krone\",\n" +
        "  \"NPR\": \"Nepalese Rupee\",\n" +
        "  \"NZD\": \"New Zealand Dollar\",\n" +
        "  \"OMR\": \"Omani Rial\",\n" +
        "  \"PAB\": \"Panamanian Balboa\",\n" +
        "  \"PEN\": \"Peruvian Nuevo Sol\",\n" +
        "  \"PGK\": \"Papua New Guinean Kina\",\n" +
        "  \"PHP\": \"Philippine Peso\",\n" +
        "  \"PKR\": \"Pakistani Rupee\",\n" +
        "  \"PLN\": \"Polish Zloty\",\n" +
        "  \"PYG\": \"Paraguayan Guarani\",\n" +
        "  \"QAR\": \"Qatari Rial\",\n" +
        "  \"RON\": \"Romanian Leu\",\n" +
        "  \"RSD\": \"Serbian Dinar\",\n" +
        "  \"RUB\": \"Russian Ruble\",\n" +
        "  \"RWF\": \"Rwandan Franc\",\n" +
        "  \"SAR\": \"Saudi Riyal\",\n" +
        "  \"SBD\": \"Solomon Islands Dollar\",\n" +
        "  \"SCR\": \"Seychellois Rupee\",\n" +
        "  \"SDG\": \"Sudanese Pound\",\n" +
        "  \"SEK\": \"Swedish Krona\",\n" +
        "  \"SGD\": \"Singapore Dollar\",\n" +
        "  \"SHP\": \"Saint Helena Pound\",\n" +
        "  \"SLL\": \"Sierra Leonean Leone\",\n" +
        "  \"SOS\": \"Somali Shilling\",\n" +
        "  \"SRD\": \"Surinamese Dollar\",\n" +
        "  \"SSP\": \"South Sudanese Pound\",\n" +
        "  \"STD\": \"São Tomé and Príncipe Dobra (pre-2018)\",\n" +
        "  \"STN\": \"São Tomé and Príncipe Dobra\",\n" +
        "  \"SVC\": \"Salvadoran Colón\",\n" +
        "  \"SYP\": \"Syrian Pound\",\n" +
        "  \"SZL\": \"Swazi Lilangeni\",\n" +
        "  \"THB\": \"Thai Baht\",\n" +
        "  \"TJS\": \"Tajikistani Somoni\",\n" +
        "  \"TMT\": \"Turkmenistani Manat\",\n" +
        "  \"TND\": \"Tunisian Dinar\",\n" +
        "  \"TOP\": \"Tongan Pa'anga\",\n" +
        "  \"TRY\": \"Turkish Lira\",\n" +
        "  \"TTD\": \"Trinidad and Tobago Dollar\",\n" +
        "  \"TWD\": \"New Taiwan Dollar\",\n" +
        "  \"TZS\": \"Tanzanian Shilling\",\n" +
        "  \"UAH\": \"Ukrainian Hryvnia\",\n" +
        "  \"UGX\": \"Ugandan Shilling\",\n" +
        "  \"USD\": \"United States Dollar\",\n" +
        "  \"UYU\": \"Uruguayan Peso\",\n" +
        "  \"UZS\": \"Uzbekistan Som\",\n" +
        "  \"VEF\": \"Venezuelan Bolívar Fuerte (Old)\",\n" +
        "  \"VES\": \"Venezuelan Bolívar Soberano\",\n" +
        "  \"VND\": \"Vietnamese Dong\",\n" +
        "  \"VUV\": \"Vanuatu Vatu\",\n" +
        "  \"WST\": \"Samoan Tala\",\n" +
        "  \"XAF\": \"CFA Franc BEAC\",\n" +
        "  \"XAG\": \"Silver Ounce\",\n" +
        "  \"XAU\": \"Gold Ounce\",\n" +
        "  \"XCD\": \"East Caribbean Dollar\",\n" +
        "  \"XDR\": \"Special Drawing Rights\",\n" +
        "  \"XOF\": \"CFA Franc BCEAO\",\n" +
        "  \"XPD\": \"Palladium Ounce\",\n" +
        "  \"XPF\": \"CFP Franc\",\n" +
        "  \"XPT\": \"Platinum Ounce\",\n" +
        "  \"YER\": \"Yemeni Rial\",\n" +
        "  \"ZAR\": \"South African Rand\",\n" +
        "  \"ZMW\": \"Zambian Kwacha\",\n" +
        "  \"ZWL\": \"Zimbabwean Dollar\"\n" +
        "}"

private const val curriencyConversionMockData = "{\n" +
        "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
        "  \"license\": \"https://openexchangerates.org/license\",\n" +
        "  \"timestamp\": 1699063201,\n" +
        "  \"base\": \"USD\",\n" +
        "  \"rates\": {\n" +
        "    \"AED\": 3.67302,\n" +
        "    \"AFN\": 72.855147,\n" +
        "    \"ALL\": 97.904048,\n" +
        "    \"AMD\": 399.068458,\n" +
        "    \"ANG\": 1.804206,\n" +
        "    \"AOA\": 832.25,\n" +
        "    \"ARS\": 350.025,\n" +
        "    \"AUD\": 1.53565,\n" +
        "    \"AWG\": 1.8,\n" +
        "    \"AZN\": 1.7,\n" +
        "    \"BAM\": 1.835786,\n" +
        "    \"BBD\": 2,\n" +
        "    \"BDT\": 109.846275,\n" +
        "    \"BGN\": 1.82332,\n" +
        "    \"BHD\": 0.377111,\n" +
        "    \"BIF\": 2816.862784,\n" +
        "    \"BMD\": 1,\n" +
        "    \"BND\": 1.360272,\n" +
        "    \"BOB\": 6.905355,\n" +
        "    \"BRL\": 4.9011,\n" +
        "    \"BSD\": 1,\n" +
        "    \"BTC\": 0.000028752929,\n" +
        "    \"BTN\": 83.337015,\n" +
        "    \"BWP\": 13.522811,\n" +
        "    \"BYN\": 3.291987,\n" +
        "    \"BZD\": 2.014408,\n" +
        "    \"CAD\": 1.36725,\n" +
        "    \"CDF\": 2460.638246,\n" +
        "    \"CHF\": 0.900498,\n" +
        "    \"CLF\": 0.031838,\n" +
        "    \"CLP\": 878.51,\n" +
        "    \"CNH\": 7.28818,\n" +
        "    \"CNY\": 7.3,\n" +
        "    \"COP\": 3989.4737,\n" +
        "    \"CRC\": 531.424028,\n" +
        "    \"CUC\": 1,\n" +
        "    \"CUP\": 25.75,\n" +
        "    \"CVE\": 104.95,\n" +
        "    \"CZK\": 22.7352,\n" +
        "    \"DJF\": 176.590572,\n" +
        "    \"DKK\": 6.953,\n" +
        "    \"DOP\": 56.301822,\n" +
        "    \"DZD\": 135.456,\n" +
        "    \"EGP\": 30.9,\n" +
        "    \"ERN\": 15,\n" +
        "    \"ETB\": 55.55,\n" +
        "    \"EUR\": 0.93145,\n" +
        "    \"FJD\": 2.2554,\n" +
        "    \"FKP\": 0.808081,\n" +
        "    \"GBP\": 0.808081,\n" +
        "    \"GEL\": 2.715,\n" +
        "    \"GGP\": 0.808081,\n" +
        "    \"GHS\": 11.93,\n" +
        "    \"GIP\": 0.808081,\n" +
        "    \"GMD\": 65.05,\n" +
        "    \"GNF\": 8650,\n" +
        "    \"GTQ\": 7.843802,\n" +
        "    \"GYD\": 209.075353,\n" +
        "    \"HKD\": 7.82555,\n" +
        "    \"HNL\": 24.493711,\n" +
        "    \"HRK\": 7.02282,\n" +
        "    \"HTG\": 132.791404,\n" +
        "    \"HUF\": 353.77,\n" +
        "    \"IDR\": 15589.1,\n" +
        "    \"ILS\": 3.92612,\n" +
        "    \"IMP\": 0.808081,\n" +
        "    \"INR\": 83.158742,\n" +
        "    \"IQD\": 1299.301807,\n" +
        "    \"IRR\": 42240,\n" +
        "    \"ISK\": 138.96,\n" +
        "    \"JEP\": 0.808081,\n" +
        "    \"JMD\": 154.739496,\n" +
        "    \"JOD\": 0.7093,\n" +
        "    \"JPY\": 149.3750151,\n" +
        "    \"KES\": 151.2,\n" +
        "    \"KGS\": 89.32,\n" +
        "    \"KHR\": 4093.171007,\n" +
        "    \"KMF\": 467.249881,\n" +
        "    \"KPW\": 900,\n" +
        "    \"KRW\": 1309.015,\n" +
        "    \"KWD\": 0.3086,\n" +
        "    \"KYD\": 0.832844,\n" +
        "    \"KZT\": 464.700649,\n" +
        "    \"LAK\": 20557.800011,\n" +
        "    \"LBP\": 14906.652861,\n" +
        "    \"LKR\": 326.315899,\n" +
        "    \"LRD\": 187.549975,\n" +
        "    \"LSL\": 18.264928,\n" +
        "    \"LYD\": 4.895,\n" +
        "    \"MAD\": 10.200093,\n" +
        "    \"MDL\": 18.03435,\n" +
        "    \"MGA\": 4515,\n" +
        "    \"MKD\": 57.396367,\n" +
        "    \"MMK\": 2098.670479,\n" +
        "    \"MNT\": 3450,\n" +
        "    \"MOP\": 8.056373,\n" +
        "    \"MRU\": 38.888682,\n" +
        "    \"MUR\": 44.250004,\n" +
        "    \"MVR\": 15.38,\n" +
        "    \"MWK\": 1134.25,\n" +
        "    \"MXN\": 17.4639,\n" +
        "    \"MYR\": 4.7305,\n" +
        "    \"MZN\": 63.899991,\n" +
        "    \"NAD\": 18.53,\n" +
        "    \"NGN\": 781.089388,\n" +
        "    \"NIO\": 36.695,\n" +
        "    \"NOK\": 11.033779,\n" +
        "    \"NPR\": 133.350257,\n" +
        "    \"NZD\": 1.667501,\n" +
        "    \"OMR\": 0.38497,\n" +
        "    \"PAB\": 1,\n" +
        "    \"PEN\": 3.771,\n" +
        "    \"PGK\": 3.73265,\n" +
        "    \"PHP\": 55.751006,\n" +
        "    \"PKR\": 278.39903,\n" +
        "    \"PLN\": 4.15155,\n" +
        "    \"PYG\": 7468.247304,\n" +
        "    \"QAR\": 3.619935,\n" +
        "    \"RON\": 4.6314,\n" +
        "    \"RSD\": 109.247327,\n" +
        "    \"RUB\": 92.850511,\n" +
        "    \"RWF\": 1232,\n" +
        "    \"SAR\": 3.751556,\n" +
        "    \"SBD\": 8.42978,\n" +
        "    \"SCR\": 13.654069,\n" +
        "    \"SDG\": 600.5,\n" +
        "    \"SEK\": 10.8829,\n" +
        "    \"SGD\": 1.3523,\n" +
        "    \"SHP\": 0.808081,\n" +
        "    \"SLL\": 20969.5,\n" +
        "    \"SOS\": 566.837505,\n" +
        "    \"SRD\": 38.1495,\n" +
        "    \"SSP\": 130.26,\n" +
        "    \"STD\": 22281.8,\n" +
        "    \"STN\": 23.5,\n" +
        "    \"SVC\": 8.744667,\n" +
        "    \"SYP\": 2512.53,\n" +
        "    \"SZL\": 18.262693,\n" +
        "    \"THB\": 35.453191,\n" +
        "    \"TJS\": 10.986688,\n" +
        "    \"TMT\": 3.5,\n" +
        "    \"TND\": 3.1845,\n" +
        "    \"TOP\": 2.402593,\n" +
        "    \"TRY\": 28.3971,\n" +
        "    \"TTD\": 6.793192,\n" +
        "    \"TWD\": 32.1044,\n" +
        "    \"TZS\": 2498,\n" +
        "    \"UAH\": 36.286242,\n" +
        "    \"UGX\": 3779.967946,\n" +
        "    \"USD\": 1,\n" +
        "    \"UYU\": 40.038192,\n" +
        "    \"UZS\": 12262.5,\n" +
        "    \"VES\": 35.128462,\n" +
        "    \"VND\": 24552.216623,\n" +
        "    \"VUV\": 118.722,\n" +
        "    \"WST\": 2.8,\n" +
        "    \"XAF\": 610.991148,\n" +
        "    \"XAG\": 0.04309045,\n" +
        "    \"XAU\": 0.00050185,\n" +
        "    \"XCD\": 2.70255,\n" +
        "    \"XDR\": 0.762736,\n" +
        "    \"XOF\": 610.991148,\n" +
        "    \"XPD\": 0.00088765,\n" +
        "    \"XPF\": 111.151551,\n" +
        "    \"XPT\": 0.00106999,\n" +
        "    \"YER\": 250.337498,\n" +
        "    \"ZAR\": 18.21191,\n" +
        "    \"ZMW\": 22.04285,\n" +
        "    \"ZWL\": 322\n" +
        "  }\n" +
        "}"