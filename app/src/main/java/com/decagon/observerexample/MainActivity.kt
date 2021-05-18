package com.decagon.observerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    lateinit var observable:Disposable
    lateinit var flowable: Disposable
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observable =  Observable.just("Hello Reactive World")
            .subscribe { value -> println(value) }


        flowable = Flowable.just("This is a Flowable")
            .subscribe(
                { value -> println("Received: $value") },
                { error -> println("Error: $error") },
                { println("Completed") }
            )

    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.add(observable)
        compositeDisposable.add(flowable)
        compositeDisposable.clear()

    }


}