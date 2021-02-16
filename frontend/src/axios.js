const axios = require('axios');

const cliente = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  timeout: 1000,
  headers: {
    Authorization:'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFsYW43OSJ9.PMfZFEBtWlGgyrtoMZBFiDfzxAtdRWD5XjdjrJchq9s'
  },
});

export default cliente;