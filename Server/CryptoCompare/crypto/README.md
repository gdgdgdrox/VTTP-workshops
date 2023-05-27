'''TASK REQUIREMENTS'''
TASK 1
1. When application starts, make API call to cryptocompare's toplist by marketcap
2. Store payload in Redis

TASK 2
3. Create a html page that has a datalist input for Coins and a radiobuttons for fiat currency.
4. Submitting the page returns the result page (API call to Single Symbol Price)
    --https://min-api.cryptocompare.com/data/price?fsym=MRK&tsyms=USD,JPY,EUR,SGD

TASK 3
5. Create a html page that shows the top 10 coins by market cap
   No. | Coin (image)(name) | Market Cap | Link
<!-- TO DO -->
6. Implement sorting
7. Implement pagination

'''URL'''
Image URL: https://www.cryptocompare.com/media/34835941/usdc.png


--------------------------------------------------------------------
{
    "Message": "Success",
    "Type": 100,
    "MetaData": {
        "Count": 735
    },
    "SponsoredData": [],
    "Data": [
        {
            "CoinInfo": {
                "Id": "1182",
                "Name": "BTC",
                "FullName": "Bitcoin",
                "Internal": "BTC",
                "ImageUrl": "/media/37746251/btc.png",
                "Url": "/coins/btc/overview",
                "Algorithm": "SHA-256",
                "ProofType": "PoW",
                "Rating": {
                    "Weiss": {
                        "Rating": "B+",
                        "TechnologyAdoptionRating": "A-",
                        "MarketPerformanceRating": "D+"
                    }
                },
                "NetHashesPerSecond": 196125250733838300000,
                "BlockNumber": 748627,
                "BlockTime": 599,
                "BlockReward": 6.25,
                "AssetLaunchDate": "2009-01-03",
                "MaxSupply": 20999999.9769,
                "Type": 1,
                "DocumentType": "Webpagecoinp"
            },
            "RAW": {
                "USD": {
                    "TYPE": "5",
                    "MARKET": "CCCAGG",
                    "FROMSYMBOL": "BTC",
                    "TOSYMBOL": "USD",
                    "FLAGS": "2050",
                    "PRICE": 23814.6,
                    "LASTUPDATE": 1660013928,
                    "MEDIAN": 23812.82,
                    "LASTVOLUME": 0.00624648,
                    "LASTVOLUMETO": 148.7556735936,
                    "LASTTRADEID": "386759843",
                    "VOLUMEDAY": 2762.886398050095,
                    "VOLUMEDAYTO": 65672276.955729045,
                    "VOLUME24HOUR": 41071.18359358,
                    "VOLUME24HOURTO": 982374249.0439537,
                    "OPENDAY": 23817.7,
                    "HIGHDAY": 23868.51,
                    "LOWDAY": 23636.06,
                    "OPEN24HOUR": 23306.6,
                    "HIGH24HOUR": 24251.9,
                    "LOW24HOUR": 23258.27,
                    "LASTMARKET": "Coinbase",
                    "VOLUMEHOUR": 1246.8070557099322,
                    "VOLUMEHOURTO": 29595398.88029438,
                    "OPENHOUR": 23738.21,
                    "HIGHHOUR": 23820.69,
                    "LOWHOUR": 23636.06,
                    "TOPTIERVOLUME24HOUR": 41071.18359358,
                    "TOPTIERVOLUME24HOURTO": 982374249.0439537,
                    "CHANGE24HOUR": 508,
                    "CHANGEPCT24HOUR": 2.179640101945372,
                    "CHANGEDAY": -3.100000000002183,
                    "CHANGEPCTDAY": -0.013015530466846854,
                    "CHANGEHOUR": 76.38999999999942,
                    "CHANGEPCTHOUR": 0.32180185447849446,
                    "CONVERSIONTYPE": "direct",
                    "CONVERSIONSYMBOL": "",
                    "SUPPLY": 19116406,
                    "MKTCAP": 455249562327.6,
                    "MKTCAPPENALTY": 0,
                    "CIRCULATINGSUPPLY": 19116406,
                    "CIRCULATINGSUPPLYMKTCAP": 455249562327.6,
                    "TOTALVOLUME24H": 422918.1325903358,
                    "TOTALVOLUME24HTO": 10075906600.622093,
                    "TOTALTOPTIERVOLUME24H": 422082.5783764166,
                    "TOTALTOPTIERVOLUME24HTO": 10056008211.239292,
                    "IMAGEURL": "/media/37746251/btc.png"
                }
            },
            "DISPLAY": {
                "USD": {
                    "FROMSYMBOL": "Ƀ",
                    "TOSYMBOL": "$",
                    "MARKET": "CryptoCompare Index",
                    "PRICE": "$ 23,814.6",
                    "LASTUPDATE": "Just now",
                    "LASTVOLUME": "Ƀ 0.006246",
                    "LASTVOLUMETO": "$ 148.76",
                    "LASTTRADEID": "386759843",
                    "VOLUMEDAY": "Ƀ 2,762.89",
                    "VOLUMEDAYTO": "$ 65,672,277.0",
                    "VOLUME24HOUR": "Ƀ 41,071.2",
                    "VOLUME24HOURTO": "$ 982,374,249.0",
                    "OPENDAY": "$ 23,817.7",
                    "HIGHDAY": "$ 23,868.5",
                    "LOWDAY": "$ 23,636.1",
                    "OPEN24HOUR": "$ 23,306.6",
                    "HIGH24HOUR": "$ 24,251.9",
                    "LOW24HOUR": "$ 23,258.3",
                    "LASTMARKET": "Coinbase",
                    "VOLUMEHOUR": "Ƀ 1,246.81",
                    "VOLUMEHOURTO": "$ 29,595,398.9",
                    "OPENHOUR": "$ 23,738.2",
                    "HIGHHOUR": "$ 23,820.7",
                    "LOWHOUR": "$ 23,636.1",
                    "TOPTIERVOLUME24HOUR": "Ƀ 41,071.2",
                    "TOPTIERVOLUME24HOURTO": "$ 982,374,249.0",
                    "CHANGE24HOUR": "$ 508.00",
                    "CHANGEPCT24HOUR": "2.18",
                    "CHANGEDAY": "$ -3.10",
                    "CHANGEPCTDAY": "-0.01",
                    "CHANGEHOUR": "$ 76.39",
                    "CHANGEPCTHOUR": "0.32",
                    "CONVERSIONTYPE": "direct",
                    "CONVERSIONSYMBOL": "",
                    "SUPPLY": "Ƀ 19,116,406.0",
                    "MKTCAP": "$ 455.25 B",
                    "MKTCAPPENALTY": "0 %",
                    "CIRCULATINGSUPPLY": "Ƀ 19,116,406.0",
                    "CIRCULATINGSUPPLYMKTCAP": "$ 455.25 B",
                    "TOTALVOLUME24H": "Ƀ 422.92 K",
                    "TOTALVOLUME24HTO": "$ 10.08 B",
                    "TOTALTOPTIERVOLUME24H": "Ƀ 422.08 K",
                    "TOTALTOPTIERVOLUME24HTO": "$ 10.06 B",
                    "IMAGEURL": "/media/37746251/btc.png"
                }
            }
        },
        {
            "CoinInfo": {
                "Id": "7605",
                "Name": "ETH",
                "FullName": "Ethereum",
                "Internal": "ETH",
                "ImageUrl": "/media/37746238/eth.png",
                "Url": "/coins/eth/overview",
                "Algorithm": "Ethash",
                "ProofType": "PoW",
                "Rating": {
                    "Weiss": {
                        "Rating": "B+",
                        "TechnologyAdoptionRating": "A",
                        "MarketPerformanceRating": "C-"
                    }
                },
                "NetHashesPerSecond": 903897273009823.5,
                "BlockNumber": 15305453,
                "BlockTime": 13.505084745762712,
                "BlockReward": 2.069755052969515,
                "AssetLaunchDate": "2015-07-30",
                "MaxSupply": -1,
                "Type": 1,
                "DocumentType": "Webpagecoinp"
            },
            "RAW": {
                "USD": {
                    "TYPE": "5",
                    "MARKET": "CCCAGG",
                    "FROMSYMBOL": "ETH",
                    "TOSYMBOL": "USD",
                    "FLAGS": "2052",
                    "PRICE": 1774.25,
                    "LASTUPDATE": 1660013928,
                    "MEDIAN": 1774.31,
                    "LASTVOLUME": 0.07259692,
                    "LASTVOLUMETO": 128.797825618,
                    "LASTTRADEID": "330837846",
                    "VOLUMEDAY": 31438.030578302263,
                    "VOLUMEDAYTO": 55722061.70370014,
                    "VOLUME24HOUR": 547592.54513598,
                    "VOLUME24HOURTO": 970756034.8047793,
                    "OPENDAY": 1778,
                    "HIGHDAY": 1786.82,
                    "LOWDAY": 1761.88,
                    "OPEN24HOUR": 1715.14,
                    "HIGH24HOUR": 1818.17,
                    "LOW24HOUR": 1708.2,
                    "LASTMARKET": "Coinbase",
                    "VOLUMEHOUR": 8522.165976050012,
                    "VOLUMEHOURTO": 15074193.39906193,
                    "OPENHOUR": 1766.36,
                    "HIGHHOUR": 1775.63,
                    "LOWHOUR": 1762.27,
                    "TOPTIERVOLUME24HOUR": 547592.54513598,
                    "TOPTIERVOLUME24HOURTO": 970756034.8047793,
                    "CHANGE24HOUR": 59.1099999999999,
                    "CHANGEPCT24HOUR": 3.44636589432932,
                    "CHANGEDAY": -3.75,
                    "CHANGEPCTDAY": -0.2109111361079865,
                    "CHANGEHOUR": 7.8900000000001,
                    "CHANGEPCTHOUR": 0.4466813107180926,
                    "CONVERSIONTYPE": "direct",
                    "CONVERSIONSYMBOL": "",
                    "SUPPLY": 121888131.6865,
                    "MKTCAP": 216260017644.7726,
                    "MKTCAPPENALTY": 0,
                    "CIRCULATINGSUPPLY": 121888131.6865,
                    "CIRCULATINGSUPPLYMKTCAP": 216260017644.7726,
                    "TOTALVOLUME24H": 3301058.5436820756,
                    "TOTALVOLUME24HTO": 5856093082.725189,
                    "TOTALTOPTIERVOLUME24H": 3293885.154406616,
                    "TOTALTOPTIERVOLUME24HTO": 5843365696.803205,
                    "IMAGEURL": "/media/37746238/eth.png"
                }
            },
            "DISPLAY": {
                "USD": {
                    "FROMSYMBOL": "Ξ",
                    "TOSYMBOL": "$",
                    "MARKET": "CryptoCompare Index",
                    "PRICE": "$ 1,774.25",
                    "LASTUPDATE": "Just now",
                    "LASTVOLUME": "Ξ 0.07260",
                    "LASTVOLUMETO": "$ 128.80",
                    "LASTTRADEID": "330837846",
                    "VOLUMEDAY": "Ξ 31,438.0",
                    "VOLUMEDAYTO": "$ 55,722,061.7",
                    "VOLUME24HOUR": "Ξ 547,592.5",
                    "VOLUME24HOURTO": "$ 970,756,034.8",
                    "OPENDAY": "$ 1,778.00",
                    "HIGHDAY": "$ 1,786.82",
                    "LOWDAY": "$ 1,761.88",
                    "OPEN24HOUR": "$ 1,715.14",
                    "HIGH24HOUR": "$ 1,818.17",
                    "LOW24HOUR": "$ 1,708.20",
                    "LASTMARKET": "Coinbase",
                    "VOLUMEHOUR": "Ξ 8,522.17",
                    "VOLUMEHOURTO": "$ 15,074,193.4",
                    "OPENHOUR": "$ 1,766.36",
                    "HIGHHOUR": "$ 1,775.63",
                    "LOWHOUR": "$ 1,762.27",
                    "TOPTIERVOLUME24HOUR": "Ξ 547,592.5",
                    "TOPTIERVOLUME24HOURTO": "$ 970,756,034.8",
                    "CHANGE24HOUR": "$ 59.11",
                    "CHANGEPCT24HOUR": "3.45",
                    "CHANGEDAY": "$ -3.75",
                    "CHANGEPCTDAY": "-0.21",
                    "CHANGEHOUR": "$ 7.89",
                    "CHANGEPCTHOUR": "0.45",
                    "CONVERSIONTYPE": "direct",
                    "CONVERSIONSYMBOL": "",
                    "SUPPLY": "Ξ 121,888,131.7",
                    "MKTCAP": "$ 216.26 B",
                    "MKTCAPPENALTY": "0 %",
                    "CIRCULATINGSUPPLY": "Ξ 121,888,131.7",
                    "CIRCULATINGSUPPLYMKTCAP": "$ 216.26 B",
                    "TOTALVOLUME24H": "Ξ 3.30 M",
                    "TOTALVOLUME24HTO": "$ 5.86 B",
                    "TOTALTOPTIERVOLUME24H": "Ξ 3.29 M",
                    "TOTALTOPTIERVOLUME24HTO": "$ 5.84 B",
                    "IMAGEURL": "/media/37746238/eth.png"
                }
            }
        },