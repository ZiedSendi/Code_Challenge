package com.believe.sr.models

case class ModelOutput (
                        upc               : Option[BigInt]=None,
                        isrc              : Option[String]=None,
                        label_name        : Option[String]=None,
                        album_name        : Option[String]=None,
                        song_id           : Option[BigInt]=None,
                        song_name         : Option[String]=None,
                        artist_name       : Option[String]=None,
                        content_type      : Option[String]=None,
                        total_net_revenue : Option[Double]=None,
                        sales_country     : Option[String]=None
                      )