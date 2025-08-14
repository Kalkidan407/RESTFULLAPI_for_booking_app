// @PutMapping("/{id}")
// public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User request) {
//     User user = userRepository.findById(id).orElseThrow();

//     user.setName(request.getName());
//     user.setEmail(request.getEmail());

//     return ResponseEntity.ok(userRepository.save(user));
// }



@GetMapping("/employees/{id}")
EntityModel<Employee> one(@PathVariable Long id) {

  Employee employee = repository.findById(id) //
      .orElseThrow(() -> new EmployeeNotFoundException(id));

  return EntityModel.of(employee, //
      linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
      linkTo(
        methodOn(EmployeeController.class).all())
        .withRel("employees"));
}