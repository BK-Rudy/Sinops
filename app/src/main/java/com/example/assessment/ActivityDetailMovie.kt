package com.example.assessment

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import com.example.assessment.databinding.ActivityDetailMovieBinding
import com.example.assessment.services.MovieService
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.gson.Gson
import java.io.InputStream
import java.net.URL

class ActivityDetailMovie : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private var mInterstitialAd: InterstitialAd? = null
    private final var tag = "ActivityDetailMovie"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this) {}
        var adRequest = AdRequest.Builder()
            .build()

        setupAdInterstitial(adRequest)

        binding.voltar.setOnClickListener{onBackPressed()}
        val data = Gson().fromJson(intent.getStringExtra(Constant.DATA), Movie::class.java)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val `is`: InputStream = URL(data.banner).getContent() as InputStream
        val draw = Drawable.createFromStream(`is`, "poster")
        binding.imgDetailView.setImageDrawable(draw)

        val movieService =MovieService()
        val movieDetail = movieService.getMovieById(data.id)

        binding.txtTitle.text = data.title
        binding.lancamento.text = data.releaseDate
        binding.rating.text = data.rating


        if (movieDetail != null) {
            binding.classificacao.text = (movieDetail.genres.map { x-> x.name }).joinToString(", ")
            binding.elenco.text = (movieDetail.credits.cast.take(5).map { x-> x.name }).joinToString(", ")
        }
        binding.sinopse.text = data.overview

    }

    private fun setupAdInterstitial(adRequest: AdRequest) {
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(tag, adError.message)
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(tag, "Ad carregado.")
                    mInterstitialAd = interstitialAd

                    if (mInterstitialAd != null) {
                        setupAdFullScreen()
                        mInterstitialAd?.show(parent)
                    } else {
                        Log.d("tag", "O ad não estava pronto para ser exibido.")
                    }
                }

                private fun setupAdFullScreen() {
                    mInterstitialAd?.fullScreenContentCallback =
                        object : FullScreenContentCallback() {
                            override fun onAdDismissedFullScreenContent() {
                                Log.d(tag, "Ad foi fechado.")
                            }

                            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                Log.d(tag, "Ad falhou ao ser exibido.")
                            }

                            override fun onAdShowedFullScreenContent() {
                                Log.d(tag, "Ad exibido em modo fullscreen.")
                                mInterstitialAd = null
                            }
                        }
                }
            })
    }


}