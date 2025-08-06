// @PutMapping("/{id}")
// public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User request) {
//     User user = userRepository.findById(id).orElseThrow();

//     user.setName(request.getName());
//     user.setEmail(request.getEmail());

//     return ResponseEntity.ok(userRepository.save(user));
// }
