package com.example.giphyapp.core.schedulers

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

open class TestSchedulers : CoreSchedulers {
	override val computation: Scheduler
		get() = Schedulers.trampoline()

	override val dbIO: Scheduler
		get() = Schedulers.trampoline()

	override val diskIO: Scheduler
		get() = Schedulers.trampoline()

	override val networkIO: Scheduler
		get() = Schedulers.trampoline()

	override val mainThread: Scheduler
		get() = Schedulers.trampoline()
}
