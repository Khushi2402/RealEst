const express = require('express')
const router = express.Router()
const User = require('../models/user.js')

router.get('/', async(req, res) => {
    try{
        const users = await User.find()
        res.json(users)
    }
    catch(err){
        res.send('Error ' + err)
    }
})

router.post('/', async(req, res) => {
    const user = new User({
        name: req.body.name,
        tech: req.body.tech,
        sub: req.body.sub
    })

    try{
        const a1 = await user.save()
        res.json(a1)
    }
    catch(err){
        res.send('Error')
    }
})

module.exports = router