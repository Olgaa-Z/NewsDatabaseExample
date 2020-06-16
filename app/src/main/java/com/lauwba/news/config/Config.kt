package com.lauwba.news.config

object Config {

    private const val Host="http://192.168.18.38/newss/" //yang bakal berganti
    const val url_berita= Host+"index.php/Webservice/select_berita"
    const val url_gambar= Host+"assets/upload_berita/"
    const val id="id"
    const val url_detail_berita= Host+"index.php/Webservice/select_by_get_berita/"
}