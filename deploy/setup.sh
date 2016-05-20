
sudo apt-get install software-properties-common -y
sudo apt-add-repository ppa:ansible/ansible -y
sudo apt-get update

sudo update-ca-certificates -f

sudo apt-get install awscli -y

sudo apt-get install ansible -y

sudo ansible-galaxy install JasonGiedymin.java
sudo ansible-galaxy install JasonGiedymin.scala
sudo ansible-galaxy install JasonGiedymin.sbt

sudo ansible-playbook -i ./playbooks/hosts ./playbooks/install.yml
