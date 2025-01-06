from locust import HttpUser, task, between

class PetstoreUser(HttpUser):
    # Waiting time between each task (randomized between 1 and 5 seconds)
    wait_time = between(1, 5)

    @task(4)
    def add_pet(self):
        """
        Simulates adding a new pet to the store.
        This task tests the API's ability to handle POST requests under load.
        """
        payload = {
            "id": 10,  # Fixed pet_id
            "name": "doggie",
            "category": {"id": 1, "name": "Dogs"},
            "photoUrls": ["string"],
            "tags": [{"id": 0, "name": "string"}],
            "status": "available"
        }
        headers = {
            "accept": "application/xml",
            "Content-Type": "application/json"
        }
        # Send the POST request to add a new pet
        self.client.post("/pet", json=payload, headers=headers)
        
    @task(2)
    def get_pet_by_id(self):
        """
        Simulates fetching the details of a pet by its ID.
        This task tests the API's ability to handle GET requests.
        """
        pet_id = 10  # Fixed pet_id for testing
        headers = {
            "accept": "application/xml"
        }
        # Send the GET request to retrieve pet information by ID
        self.client.get(f"/pet/{pet_id}", headers=headers)

    @task(1)
    def delete_pet(self):
        """
        Simulates deleting a pet from the store.
        This task tests the API's ability to handle DELETE requests.
        """
        pet_id = 10  # Fixed pet_id for deletion
        headers = {
            "accept": "*/*",
            "api_key": "1"
        }
        # Send the DELETE request to remove a pet by its ID
        self.client.delete(f"/pet/{pet_id}", headers=headers)

    @task(1)
    def delete_invalid_pet(self):
        """
        Simulates trying to delete a pet that does not exist.
        This task checks how the API handles errors when an invalid ID is provided.
        """
        invalid_pet_id = 99999  # A pet_id that does not exist
        headers = {
            "accept": "*/*",
            "api_key": "1"
        }
        # Send the DELETE request with an invalid pet_id to test error handling
        self.client.delete(f"/pet/{invalid_pet_id}", headers=headers)
