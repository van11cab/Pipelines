resource "aws_instance" "pipeline_ouput" {
  ami           = "ami-08f7912c15ca96832"
  instance_type = "t2.micro"

  tags = {
    Name = "Output"
  }
}

