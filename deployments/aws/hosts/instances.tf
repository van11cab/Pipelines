resource "aws_instance" "pipeline_ouput" {
  ami           = "ami-08f7912c15ca96832"
  instance_type = "t2.micro"
  vpc_security_group_ids = ["sg-03cfac34d03e9d681"]
  key_name = "Keypair_viaPutty"

  tags = {
    Name = "Output"
  }
}

resource "aws_instance" "pipeline_ouput2" {
  ami           = "ami-08f7912c15ca96832"
  instance_type = "t2.micro"
  vpc_security_group_ids = ["sg-03cfac34d03e9d681"]
  key_name = "Keypair_viaPutty"

  tags = {
    Name = "Output_2"
  }
}

resource "aws_ec2_instance_state" "for_output" {
  instance_id = resource.aws_instance.pipeline_ouput.id
  state = "running"
}

resource "aws_ec2_instance_state" "for_output2" {
  instance_id = resource.aws_instance.pipeline_ouput2.id
  state = "running"
}
