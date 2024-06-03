#!/bin/bash
docker run --name cmir-mgt --network cmir-mgt --restart always -p 8080:8080 -d ruoyi.cnli.gic.cmir.cc/cmir-mgt
