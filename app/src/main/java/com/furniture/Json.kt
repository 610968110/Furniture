/*
 */
package com.furniture

import com.furniture.bean.SocketBean
import com.furniture.bean.json.AllState
import com.furniture.constant.INet
import com.furniture.utils.GsonUtil
import lbx.xtoollib.phone.xLogUtil

/**
 * Author: liboxin
 * Date: 2020/6/2
 * Time: 16:56
 * Desc:
 */
val warningJson = "{\n" +
    "    \"service\": \"deviceJoin\",\n" +
    "    \"timestamp\": 946675880,\n" +
    "    \"type\": \"REPORT\",\n" +
    "    \"uuid\": \"b21adaaa81582330e15a71db5ea59770\",\n" +
    "    \"params\": {\n" +
    "        \"devices\": [\n" +
    "            {\n" +
    "                \"devid\": \"HomeWar\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"ning\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1L2Swit\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"ch\": 1\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1L3Swit\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"ch\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1AHU1Ct\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"rl\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1AHU1C\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"M\": 4\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1AHU1T\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"get\": 250,\n" +
    "                    \"set\": 200\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1AHU1CF\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"S\": 2\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1Curt1Ctrl\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"on\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1Curt1L\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"set\": 0,\n" +
    "                    \"get\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1Gau1Ctrl\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"on\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1Gau1L\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"set\": 0,\n" +
    "                    \"get\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1FAU1C\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"trl\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1FAU1FA\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"auto\": 0,\n" +
    "                    \"IT\": 250,\n" +
    "                    \"OT\": 230,\n" +
    "                    \"CO2\": 8000\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1FAU1CF\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"S\": 3\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1FH1C\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"trl\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1FH1Auto\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"Ctrl\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1FH1T\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"get\": 230,\n" +
    "                    \"set\": 250\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R2AHU1Ct\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"rl\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R2AHU1C\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"M\": 4\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R2AHU1T\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"get\": 250,\n" +
    "                    \"set\": 200\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R2AHU1CF\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"S\": 2\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R2L1S\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"witch\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R2L2S\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"witch\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3AHU1Ct\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"rl\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3AHU1C\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"M\": 4\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3AHU1T\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"get\": 200,\n" +
    "                    \"set\": 200\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3AHU1CF\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"S\": 4\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3L1S\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"witch\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3L2S\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"witch\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3L3S\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"witch\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3Curt1Ctrl\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"on\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3Curt1L\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"set\": 0,\n" +
    "                    \"get\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3Gau1Ctrl\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"on\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3Gau1L\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"set\": 0,\n" +
    "                    \"get\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R4AHU1Ct\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"rl\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R4AHU1C\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"M\": 4\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R4AHU1T\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"set\": 200,\n" +
    "                    \"get\": 200\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R4AHU1CF\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"S\": 4\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R5AHU1Ct\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"rl\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R5AHU1C\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"M\": 4\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R5AHU1T\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"set\": 200,\n" +
    "                    \"get\": 200\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R5AHU1CF\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"S\": 4\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R6AHU1Ct\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"rl\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R6AHU1C\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"M\": 4\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R6AHU1T\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"set\": 200,\n" +
    "                    \"get\": 200\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R6AHU1CF\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"S\": 4\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"RHome\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"In\": 1,\n" +
    "                    \"Warning\": 1\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1Der\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"Temp\": 250,\n" +
    "                    \"TempL\": 3,\n" +
    "                    \"Humi\": 530,\n" +
    "                    \"HumiL\": 3,\n" +
    "                    \"PM25\": 130,\n" +
    "                    \"PM25L\": 1,\n" +
    "                    \"HCHO\": 0,\n" +
    "                    \"HCHOL\": 1,\n" +
    "                    \"TVOC\": 0,\n" +
    "                    \"TVOCL\": 1,\n" +
    "                    \"CO2\": 4000,\n" +
    "                    \"CO2L\": 4000\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R1ComfM\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"ode\": 0\n" +
    "                }\n" +
    "            },\n" +
    "            {\n" +
    "                \"devid\": \"R3ComfM\",\n" +
    "                \"procode\": \"NULL\",\n" +
    "                \"online\": \"on\",\n" +
    "                \"field\": {\n" +
    "                    \"ode\": 0\n" +
    "                }\n" +
    "            }\n" +
    "        ]\n" +
    "    }\n" +
    "}"


fun onSocketMsg() {
    val bean = SocketBean(warningJson)
    xLogUtil.e("xys", "~~~bean:${bean.json}")
    val result = GsonUtil.getInstance().getResult(bean.json)
    if (INet.ALL_TYPE == result.type) {
        val allState = GsonUtil.getInstance().fromJson(bean.json, AllState::class.java)
        xLogUtil.e("xys", "~~~allState:${allState.toJson()}")
        xLogUtil.e("xys", "~~~allState.params:${allState.params.toJson()}")
        allState.params.devices.forEach {
            xLogUtil.e("xys", "~~~allState.params.devices:${it.devid}")
        }
        val w = allState.params.devices.firstOrNull { it.devid == "RHome" }?.field?.warning
        xLogUtil.e("xys", "w:${w}")
    }
}