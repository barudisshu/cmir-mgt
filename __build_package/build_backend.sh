#!/bin/bash
docker build -f $(dirname $0)/backend_compose/Dockerfile -t ruoyi.cnli.gic.cmir.cc/cmir-mgt:latest .
# docker push ruoyi.cnli.gic.cmir.cc/cmir-mgt:latest
