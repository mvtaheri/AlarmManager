package com.vahid.alarmmanager

interface AlarmScheduler {
    fun scheduler(item: AlarmItem)
    fun cancel(item: AlarmItem)
}