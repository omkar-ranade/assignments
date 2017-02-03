package com.cybage

class EmployeeController {

    def index() { 

	}
	def save() {
		def employee = new Employee(params)
		employee.save()
		render(view: "success")
		//render "Success!"
	}
	def search() {

	}
	def searchProcess() {
		def employee = new Employee(params)
		employee = new Employee(firstName: employee.firstName, lastName:'Agrawal', city:'Jabalpur', DOB:new Date())
		[ employee:employee ]
	}
}
