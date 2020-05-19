package org.tensorflow.framework.datasets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.tensorflow.tools.Shape;

import static org.junit.Assert.assertEquals;

public class DatasetInfoTest {

  public String getTestDatasetInfo() {
    return "{\n"
        + "  \"citation\": \"@article{lecun2010mnist,\\n  title={MNIST handwritten digit database},\\n  author={LeCun, Yann and Cortes, Corinna and Burges, CJ},\\n  journal={ATT Labs [Online]. Available: http://yann. lecun. com/exdb/mnist},\\n  volume={2},\\n  year={2010}\\n}\\n\",\n"
        + "  \"description\": \"The MNIST database of handwritten digits.\",\n"
        + "  \"downloadSize\": \"11594722\",\n"
        + "  \"location\": {\n"
        + "    \"urls\": [\n"
        + "      \"http://yann.lecun.com/exdb/mnist/\"\n"
        + "    ]\n"
        + "  },\n"
        + "  \"name\": \"mnist\",\n"
        + "  \"schema\": {\n"
        + "    \"feature\": [\n"
        + "      {\n"
        + "        \"name\": \"image\",\n"
        + "        \"shape\": {\n"
        + "          \"dim\": [\n"
        + "            {\n"
        + "              \"size\": \"28\"\n"
        + "            },\n"
        + "            {\n"
        + "              \"size\": \"28\"\n"
        + "            },\n"
        + "            {\n"
        + "              \"size\": \"1\"\n"
        + "            }\n"
        + "          ]\n"
        + "        },\n"
        + "        \"type\": \"INT\"\n"
        + "      },\n"
        + "      {\n"
        + "        \"name\": \"label\",\n"
        + "        \"type\": \"INT\"\n"
        + "      }\n"
        + "    ]\n"
        + "  },\n"
        + "  \"splits\": [\n"
        + "    {\n"
        + "      \"name\": \"test\",\n"
        + "      \"numBytes\": \"3150056\",\n"
        + "      \"numShards\": \"1\",\n"
        + "      \"shardLengths\": [\n"
        + "        \"10000\"\n"
        + "      ],\n"
        + "      \"statistics\": {\n"
        + "        \"features\": [\n"
        + "          {\n"
        + "            \"name\": \"image\",\n"
        + "            \"numStats\": {\n"
        + "              \"commonStats\": {\n"
        + "                \"numNonMissing\": \"10000\"\n"
        + "              },\n"
        + "              \"max\": 255.0\n"
        + "            }\n"
        + "          },\n"
        + "          {\n"
        + "            \"name\": \"label\",\n"
        + "            \"numStats\": {\n"
        + "              \"commonStats\": {\n"
        + "                \"numNonMissing\": \"10000\"\n"
        + "              },\n"
        + "              \"max\": 9.0\n"
        + "            }\n"
        + "          }\n"
        + "        ],\n"
        + "        \"numExamples\": \"10000\"\n"
        + "      }\n"
        + "    },\n"
        + "    {\n"
        + "      \"name\": \"train\",\n"
        + "      \"numBytes\": \"18872042\",\n"
        + "      \"numShards\": \"1\",\n"
        + "      \"shardLengths\": [\n"
        + "        \"60000\"\n"
        + "      ],\n"
        + "      \"statistics\": {\n"
        + "        \"features\": [\n"
        + "          {\n"
        + "            \"name\": \"image\",\n"
        + "            \"numStats\": {\n"
        + "              \"commonStats\": {\n"
        + "                \"numNonMissing\": \"60000\"\n"
        + "              },\n"
        + "              \"max\": 255.0\n"
        + "            }\n"
        + "          },\n"
        + "          {\n"
        + "            \"name\": \"label\",\n"
        + "            \"numStats\": {\n"
        + "              \"commonStats\": {\n"
        + "                \"numNonMissing\": \"60000\"\n"
        + "              },\n"
        + "              \"max\": 9.0\n"
        + "            }\n"
        + "          }\n"
        + "        ],\n"
        + "        \"numExamples\": \"60000\"\n"
        + "      }\n"
        + "    }\n"
        + "  ],\n"
        + "  \"supervisedKeys\": {\n"
        + "    \"input\": \"image\",\n"
        + "    \"output\": \"label\"\n"
        + "  },\n"
        + "  \"version\": \"3.0.1\"\n"
        + "}";
  }

  @Test
  public void datasetInfoTest() {
    Gson gson =
        new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    DatasetInfo info = gson.fromJson(getTestDatasetInfo(), DatasetInfo.class);

    assertEquals("mnist", info.getName());
    assertEquals(new Version(3, 0, 1), info.getVersion());
    assertEquals("The MNIST database of handwritten digits.", info.getDescription());

    assertEquals(2, info.getSplits().length);

    DatasetInfo.SplitInfo test = info.getSplits()[0];
    DatasetInfo.SplitInfo train = info.getSplits()[1];
    assertEquals("test", test.getName());
    assertEquals("train", train.getName());

    DatasetInfo.Schema.Feature trainSchema = info.getSchema().getFeatures()[0];
    DatasetInfo.Schema.Feature testSchema = info.getSchema().getFeatures()[1];

    assertEquals("image", trainSchema.getName());
    assertEquals(Shape.of(28, 28, 1), trainSchema.getShape());
    assertEquals("INT", trainSchema.getDtype());

    assertEquals("label", testSchema.getName());
//    assertThrows(NullPointerException.class, testSchema::getShape);
    assertEquals("INT", testSchema.getDtype());
  }
}
