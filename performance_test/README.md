# Locust Performance Testing for Petstore APIs

## **Overview**
This project uses [Locust](https://locust.io/) to conduct performance testing on Petstore APIs. The tests simulate various user behaviors, such as retrieving, creating, and deleting pets, to evaluate the APIs' performance under different conditions.

---

## **Installation**

### **Prerequisites**
- Python 3.7 or higher
- Pip (Python package manager)
- Internet access to connect to the Petstore API

### **Setup Steps**
1. Create a virtual environment (optional but recommended):
   ```bash
   python -m venv venv
   source venv/bin/activate  # For Linux/MacOS
   venv\Scripts\activate   # For Windows
   ```

2. Install dependencies:
   ```bash
   pip install locust
   ```

3. Verify Locust installation:
   ```bash
   locust -h
   ```
   If the above command doesn't work, try using:
   ```bash
   python -m locust -h
   ```

---

## **Configuration**
The tests are defined in the `locustfile.py` file. Each task corresponds to a specific API operation:
- **GET /pet/{id}**: Retrieve a pet by its ID.
- **POST /pet**: Add a new pet.
- **DELETE /pet/{id}**: Delete a pet by its ID.

Update the `locustfile.py` file if needed to adjust headers, payloads, or endpoints.

---

## **Execution**

### **Run Locust**
1. Start Locust from the project directory:
   ```bash
   python -m locust -f locustfile.py --host=https://petstore3.swagger.io/api/v3
   ```

2. Open the Locust web interface by navigating to:
   ```
   http://localhost:8089
   ```

### **Configure the Test**
- **Number of Users**: Define the number of concurrent users to simulate.
- **Spawn Rate**: Specify how quickly users are added.
- **Host**: Ensure the host is set to `https://petstore3.swagger.io/api/v3`.

### **Monitor Results**
The web interface provides real-time metrics, including:
- Request per second (RPS)
- Average response time
- Error rates

---

## **Results Export**
To export test results as CSV files, use the following command:
```bash
locust -f locustfile.py --host=https://petstore3.swagger.io/api/v3 --csv=results
```
This will generate:
- `results_stats.csv`: Contains performance metrics.
- `results_failures.csv`: Lists failed requests.

---

## **Troubleshooting**

### **Locust command not recognized**
If you see the error `locust: The term 'locust' is not recognized`:
1. Ensure Python and Pip are correctly installed.
2. Verify that Locust is installed with:
   ```bash
   pip show locust
   ```
3. Add Python's `Scripts` directory to your system PATH:
   - Example: `C:\Users\YourUsername\AppData\Local\Programs\Python\PythonXX\Scripts`

### **Running Locust using Python**
If the `locust` command fails, try:
```bash
python -m locust -f locustfile.py --host=https://petstore3.swagger.io/api/v3
```

---

## **Next Steps**
- Extend test cases to cover additional endpoints.
- Analyze exported results and create performance improvement recommendations.

---

## **References**
- [Locust Documentation](https://docs.locust.io/en/stable/)
- [Swagger Petstore API](https://petstore3.swagger.io/)
