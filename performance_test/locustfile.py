from locust import HttpUser, task, between

class PetstoreUser(HttpUser):
    wait_time = between(1, 5)

@task(1)
def add_pet(self):
    payload = {
        "id": 10,
        "name": "doggie",
        "category": {
            "id": 1,
            "name": "Dogs"
        },
        "photoUrls": [
            "string"
        ],
        "tags": [
            {
                "id": 0,
                "name": "string"
            }
        ],
        "status": "available"
    }
    headers = {
        "accept": "application/xml",
        "Content-Type": "application/json"
    }
    self.client.post("/pet", json=payload, headers=headers)


@task(2)
def get_pet_by_id(self):
    headers = {
        "accept": "application/xml"
    }
    pet_id = 10
    self.client.get(f"/pet/{pet_id}", headers=headers)


@task(3)
def delete_pet(self):
    headers = {
        "accept": "*/*",
        "api_key": "1"
    }
    pet_id = 10
    self.client.delete(f"/pet/{pet_id}", headers=headers)