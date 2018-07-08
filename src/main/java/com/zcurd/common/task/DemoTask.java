//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common.task;

import com.jfinal.plugin.cron4j.ITask;

public class DemoTask implements ITask {
	public DemoTask() {
	}

	public void run() {
		System.out.println("demo run!");
	}

	public void stop() {
		System.out.println("demo stop!");
	}
}
