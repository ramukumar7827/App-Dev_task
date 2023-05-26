const express = require('express');
const axios = require('axios');

const app = express();
const port = 3000;

app.get('/api/questions', async (req, res) => {
  try {
    const amount = req.query.amount
    const category = req.query.category
    const difficulty = req.query.difficulty


    const apiUrl = "https://opentdb.com/api.php?amount=10&category=21&difficulty=medium&type=multiple";

    const response = await axios.get(apiUrl);
    const questions = response.data.results;

    res.json(questions);
  } catch (error) {
    console.error('Error fetching questions:', error);
    res.status(500).json({ error: 'Failed to fetch questions' });
  }
});

app.listen(port, () => {
  console.log(`Server listening at http:
});
